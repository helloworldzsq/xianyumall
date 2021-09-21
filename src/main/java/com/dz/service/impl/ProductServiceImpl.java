package com.dz.service.impl;

import com.alibaba.fastjson.JSON;
import com.dz.pojo.Product;
import com.dz.mapper.ProductMapper;
import com.dz.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dz.util.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dz
 * @since 2021-09-12
 */
@Service
@CacheNamespace(implementation= MybatisRedisCache.class,eviction= MybatisRedisCache.class)
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Resource
    private RestHighLevelClient restHighLevelClient;
    //获取状态为0的所有产品  并解析数据放入ES中
    public List<Product> getProducts(){
        List<Product> products = productMapper.selectList(null);
        List<Product> list = new ArrayList<>();
        for (Product product : products) {
            if (product.getStatus()==0)
                list.add(product);
        }
        return list;
    }
    //获取自己发布的产品（还未交易）
    public List<Product> getMyProducts(String name){
        List<Product> products = productMapper.selectList(null);
        List<Product> list = new ArrayList<>();
        for (Product product : products) {
            if (product.getUsername().equals(name) && product.getStatus()==0){
                list.add(product);
            }
        }
        return list;
    }
    //解析所有的数据放入ES中
    public void parseProducts() throws IOException {
//        批量存放
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        List<Product> products = getProducts();
        for (int i = 0; i < products.size(); i++) {
            bulkRequest.add(new IndexRequest("xianyu_mall")
                    .source(JSON.toJSONString(products.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }
    //解析单个数据
    public void parseProduct(Product product) throws IOException {
        IndexRequest request = new IndexRequest("xianyu_mall");
        //将数据放入请求
        request.source(JSON.toJSONString(product), XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }
    //条件搜索
    public List<Map<String,Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException {
        if (pageNo < 1)
            pageNo = 1;
        //条件搜索
        SearchRequest searchRequest = new SearchRequest("xianyu_mall");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);

        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(termQueryBuilder);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false); //关闭多个高亮显示,只显示第一个
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
            //获取所有的高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap(); //原来的字段，
            //解析高亮的字段，将原来的字段替换为高亮的字段
            if (title != null){
                //高亮字段
                Text[] fragments = title.fragments();
                String n_title = "";
                for (Text text : fragments) {
                    n_title += text;
                }
                sourceAsMap.put("title",n_title); //执行替换
            }
            list.add(hit.getSourceAsMap());
        }
        return list;
    }
}
