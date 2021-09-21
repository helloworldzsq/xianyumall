package com.dz.controller.back;
import com.dz.service.impl.ProductServiceImpl;
import com.dz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/back")
public class WelcomeController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/toWelcome")
    public String toWelcome(Model model){
        int productNum = productService.list().size();
        int userNum = userService.list().size();
        model.addAttribute("productNum",productNum);
        model.addAttribute("userNum",userNum);
        return "backstage/welcome";
    }
}
