package com.dz.controller.mall;

import com.dz.pojo.Product;
import com.dz.service.impl.ProductServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    private ProductServiceImpl productService;

    @RequestMapping("/search{num}")
    public String search(@PathVariable("num")int num,String keyword, Model model) throws IOException {
        List<Map<String, Object>> maps = productService.searchPage(keyword, num, 20);
        List<Product> products = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Integer pid=(Integer)map.get("pid");
            String title =(String) map.get("title");
            String pictureUrl =(String) map.get("pictureUrl");
            Integer price= (Integer) map.get("price");
            String username =(String) map.get("username");
            Integer status = (Integer) map.get("status");
            Date time = (Date) map.get("time");
            Product product = new Product(pid,title,pictureUrl,price,username,status,time);
            if (products.contains(product))
                continue;
            products.add(product);
        }
        int pageNum = products.size();
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("num",num);
        model.addAttribute("products",products);
        return "mall/search-result";
    }
}
