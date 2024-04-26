package com.mysite.kws.service;

import com.mysite.kws.entity.Question;
import com.mysite.kws.exception.DataNotFoundExcetion;
import com.mysite.kws.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 서비스: 스프링에서 데이터 처리를 위해 작성하는 클래스
 * - @Service 어노테이션을 통해 스프링에게 서비스임을 알림
 * - 복잡한 코드를 모듈화할 수 있다.
 * - 엔티티 객체를 DTO 객체로 변환할 수 있다.
 *  (엔티티 객체를 직접 사용하면 민감한 정보가 노출될 수 있음)
 * */
@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundExcetion("question not found");
        }
    }

    public void create(String title, String content) {
        Question q = new Question();
        q.setTitle(q.getTitle());
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
}
