package com.dz.controller.mall;

import com.dz.pojo.Product;
import com.dz.service.impl.ProductServiceImpl;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.security.Principal;


@Controller
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;
    @RequestMapping("/toAdd")
    public String toAddproduct(Principal principal,Model model){
        String name = principal.getName();
        model.addAttribute("username",name);
        return "mall/addProduct";
    }
    //去商品详情页
    @RequestMapping("/toProduct{pid}")
    public String toProduct(@PathVariable("pid")Integer pid, Model model){
        Product product = productService.getById(pid);
        model.addAttribute("product",product);
        return "mall/product-single";
    }
}
