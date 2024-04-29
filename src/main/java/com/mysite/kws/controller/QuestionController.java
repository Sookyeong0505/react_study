package com.mysite.kws.controller;

import com.mysite.kws.entity.Question;
import com.mysite.kws.entity.QuestionForm;
import com.mysite.kws.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        // DB에서 질문 목록을 조회
        List<Question> questions = questionService.getList();

        // 조회한 질문 목록을 모델에 담아서 뷰로 전달
        model.addAttribute("questions", questions);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        // DB에서 질문 상세정보를 조회
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String create(QuestionForm questionForm) {
        // GET 방식으로 URL이 요청되더라도 th:object에 의해 QuestionForm 객체가 필요함.
        // 매개변수로 바인딩한 객체는 Model 객체로 전달하지 않아도 템플릿에서 사용 가능.

        return "question_form";
    }

    @PostMapping("/create")
    public String create(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        // BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치해야 한다

        // 유효성 검사 실패 시 다시 입력 폼으로 이동
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getTitle(), questionForm.getContent());
        return "redirect:/question/list";
    }

}
