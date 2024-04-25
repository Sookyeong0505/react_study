package com.mysite.kws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionController {

    @GetMapping("/question/list")
    @ResponseBody
    public String list() {
        System.out.println("question list controller");
        return "question list";
    }

    @GetMapping("/question/detail")
    @ResponseBody
    public String detail() {
        System.out.println("question detail controller");
        return "question detail";
    }
}
