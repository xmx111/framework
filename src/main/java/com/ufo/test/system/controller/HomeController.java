package com.ufo.test.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("homeController")
@RequestMapping("/")
public class HomeController {

    @RequestMapping("index.htm")
    public String index(ModelMap map) {
        return "index";
    }
    
    @RequestMapping("login.htm")
    public String login(String redirect, ModelMap map) {
        map.put("redirect", redirect);
        return "login";
    }
    
}
