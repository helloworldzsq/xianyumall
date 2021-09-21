package com.dz.controller.mall;

import com.dz.pojo.Cart;
import com.dz.pojo.Product;
import com.dz.service.impl.CartServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartServiceImpl cartService;
    //将商品添加到心愿清单中
    @RequestMapping("/toCart")
    public String toCart(Principal principal ,Model model){
        String name = principal.getName();
        List<Product> products = cartService.getCarts(name);
        model.addAttribute("products",products);
        return "mall/cart";
    }
    @RequestMapping("addCart{pid}")
    public String addCart(@PathVariable("pid")Integer pid,Principal principal){
        //获取当前登录用户的名字
        String name = principal.getName();
        Cart cart = new Cart(name, pid);
        cartService.save(cart);
        return "redirect:/";
    }
    //将产品从购物车删除
    @RequestMapping("/delectPro{pid}")
    public String delectProduct(@PathVariable("pid")Integer pid,Principal principal){
        String name = principal.getName();
        cartService.delectProduct(name,pid);
        return "redirect:/toCart";
    }
}
