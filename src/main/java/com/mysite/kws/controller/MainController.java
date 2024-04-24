package com.mysite.kws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/")
    public String main() {
        System.out.println("main controller");
        return "index";
    }

    @GetMapping("/kws")
    @ResponseBody
    public String kws() {
        System.out.println("kws controller");
        return "안녕하세요. kws에 오신 것을 환영합니다.";
    }
}
