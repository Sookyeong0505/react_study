package com.mysite.kws.controller;

import com.mysite.kws.entity.Question;
import com.mysite.kws.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionRepository questionRepository;

    @GetMapping("/question/list")
    public String list(Model model) {
        // DB에서 질문 목록을 조회
        List<Question> questions = questionRepository.findAll();

        // 조회한 질문 목록을 모델에 담아서 뷰로 전달
        model.addAttribute("questions", questions);
        return "question_list";
    }

//    @GetMapping("/question/detail")
//    public String detail(Model model) {
//        // DB에서 질문 상세정보를 조회
//
//        return "question_detail";
//    }
}
