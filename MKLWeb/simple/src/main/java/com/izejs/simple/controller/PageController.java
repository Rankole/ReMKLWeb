package com.izejs.simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @GetMapping("/page/{pageName}")
    public String pageRoute(@PathVariable String pageName){
        return pageName.replaceAll(">", "/");
    }

    @GetMapping("/")
    public String index(){
        return "login/login";
    }
}
//test point
//test push