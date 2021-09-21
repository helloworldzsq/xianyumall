package com.dz.controller.mall;

import com.dz.pojo.User;
import com.dz.service.UserService;
import com.dz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RegisterController {
    @Autowired
   private UserServiceImpl userService;
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "mall/register";
    }

    @RequestMapping("/register")
    public String register(User user, Model model){
        List<String> names = userService.getNames();
        //确保有惟一的用户名
        if (names.contains(user.getUsername())){
            model.addAttribute("msg","用户名已存在");
            return "mall/register";
        }
        userService.save(user);
        return "redirect:/";
    }
}
