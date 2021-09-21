package com.dz.controller.mall;

import com.dz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "mall/login";
    }
//    @RequestMapping("/login")
//    public String login(String username, String password, Model model){
//        boolean res = userService.validateLogin(username, password);
//        if (!res) {
//            model.addAttribute("msg", "账号或密码错误，请重新输入");
//            return "mall/login";
//        }
//        return "index";
//    }

}
