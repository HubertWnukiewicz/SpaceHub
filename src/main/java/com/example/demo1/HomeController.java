package com.example.demo1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/test")
    public String showTest() {
        return "test";
    }
}
