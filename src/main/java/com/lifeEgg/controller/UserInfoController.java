package com.lifeEgg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserInfoController {

	
    @RequestMapping(value="/user")
    public String userInfo(){

        return "user_info";
    }
}