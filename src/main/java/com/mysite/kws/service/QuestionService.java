package com.mysite.kws.service;

import com.mysite.kws.entity.Question;
import com.mysite.kws.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}