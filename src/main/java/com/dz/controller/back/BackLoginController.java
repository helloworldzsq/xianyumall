package com.dz.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/back")
public class BackLoginController {
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "backstage/login";
    }
    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        if (username.equals("admin") && password.equals("admin"))
            return "backstage/index";
        model.addAttribute("msg","用户名或密码错误");
        return "backstage/login";
    }
}
