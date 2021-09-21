package com.dz;

import com.dz.mapper.UserMapper;
import com.dz.pojo.User;
import com.dz.service.UserService;
import com.dz.service.impl.ProductServiceImpl;
import com.dz.service.impl.UserServiceImpl;
import com.dz.util.RedisUtil;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class XianyumallApplicationTests {

    @Autowired
    private UserMapper userService;
    @Resource
    private ProductServiceImpl productService;
    @Resource
    RestHighLevelClient client;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Test
    void contextLoads() throws IOException {
//        GetRequest request = new GetRequest("xianyu_mall","NF6653sBb2m7ivqArNS5");
//        //判断数据是否存在
//        boolean exists = client.exists(request, RequestOptions.DEFAULT);
//        GetResponse fields = client.get(request, RequestOptions.DEFAULT);
//        System.out.println(fields.getSourceAsString());
        productService.parseProducts();
        List<Map<String, Object>> maps = productService.searchPage("123", 1, 10);
        System.out.println(maps.size());
//        DeleteRequest request = new DeleteRequest("xianyu_mall", "RF6O6HsBb2m7ivqA3NTV");
//        request.timeout("1s");
//        DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
//        System.out.println(delete.status());
    }
    @Test
    public void test(){
        redisUtil.set("name","hello");
        System.out.println(redisUtil.get("name"));
    }

}
