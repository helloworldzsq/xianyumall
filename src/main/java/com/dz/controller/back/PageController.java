package com.dz.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/back")
public class PageController {

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "backstage/index";
    }
}
