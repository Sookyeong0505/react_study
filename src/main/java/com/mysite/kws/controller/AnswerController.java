package com.mysite.kws.controller;

import com.mysite.kws.entity.Question;
import com.mysite.kws.service.AnswerService;
import com.mysite.kws.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String content) {
        // DB에서 질문 상세정보를 조회
        Question question = this.questionService.getQuestion(id);
        this.answerService.createAnswer(question, content);

        return String.format("redirect:/question/detail/%s", id);
    }
}
