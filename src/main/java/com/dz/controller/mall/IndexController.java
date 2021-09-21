package com.dz.controller.mall;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dz.pojo.Product;
import com.dz.service.impl.ProductServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ProductServiceImpl productService;
    @RequestMapping({"/","/index"}) //可以填写多个路径
    public String index(Model model) throws IOException {
        return "redirect:/page1";
    }
    //去我发布的商品详情页  已发布但还没有被交易
    @RequestMapping("/toMy")
    public String toMy(Principal principal,Model model){
        String name = principal.getName();
        List<Product> myProducts = productService.getMyProducts(name);
        if (myProducts.isEmpty())
            return "mall/My";
        model.addAttribute("myProducts",myProducts);
        return "mall/My";
    }
    @RequestMapping("deleteMyPro{pid}")
    public String deleteMyPro(@PathVariable("pid")Integer pid){
        Product product = productService.getById(pid);
        product.setStatus(1);
        productService.updateById(product);
        return "redirect:/toMy";
    }
    //num 页数
    @RequestMapping("/page{num}")
    public String page(@PathVariable("num")int num,Model model){
        List<Product> lists = productService.getProducts();
        //查出一共有多少页
        int size = lists.size();
        // 页面数
        int pageNum=0;
        if (size<=20){
            pageNum=1;
        }
        else{
            pageNum=size%20==0?size/20:size/20+1;
        }
        Page<Product> page = new Page<>(num,20);
        productService.page(page);
        List<Product> records = page.getRecords();
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("num",num);
        model.addAttribute("products",records);
        return "index";
    }
}
