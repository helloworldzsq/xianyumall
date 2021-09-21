package com.dz.controller.back;


import com.dz.pojo.User;
import com.dz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/back")
public class UserListController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/toUserList")
    public String toUserList(Model model){
        List<User> users = userService.list();
        model.addAttribute("users",users);
        return "backstage/user_list";
    }
    @ResponseBody
    @RequestMapping("/deleteUser")
    public String deleteUser(Integer uid){
        userService.removeById(uid);
        return "OK";
    }
}
