package com.dz.controller.mall;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayController {
    //去支付界面
    @RequestMapping("/toPay{pid}")
    public String toPay(@PathVariable("pid")Integer pid){
        return "mall/pay";
    }
}
