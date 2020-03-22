package com.project.english.gemmy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularRequestController {
    @RequestMapping("/student/**")
    public String angular() {
        return "forward:/";
    }

}
