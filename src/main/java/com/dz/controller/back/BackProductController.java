package com.dz.controller.back;

import com.dz.pojo.Product;
import com.dz.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/back")
public class BackProductController {
    @Autowired
    private ProductServiceImpl productService;
    //获取所有的数据
    @RequestMapping("/toProductList")
    public String toProductList(Model model){
        List<Product> products = productService.list();
        model.addAttribute("products",products);
        return "backstage/product_list";
    }
    @ResponseBody
    @RequestMapping("/deleteProduct")
    public String deleteProduct(Integer pid){
        productService.removeById(pid);
        return "OK";
    }
}
