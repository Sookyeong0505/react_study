package com.mysite.kws;

import com.mysite.kws.entity.Answer;
import com.mysite.kws.entity.Question;
import com.mysite.kws.repository.AnswerRepository;
import com.mysite.kws.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class KwsApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void testJpa1() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getTitle());
    }

    @Test
    void testJpa2() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()){
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getTitle());
        }
    }
    @Test
    @DisplayName("findByTitle 테스트")
    void testJpa3() {
        Question q = this.questionRepository.findByTitle("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("findByTitleAndContent 테스트")
    void testJpa4() {
        Question q = this.questionRepository.findByTitleAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("findByTitleLike 테스트")
    void testJpa5() {
        List<Question> list = this.questionRepository.findByTitleLike("%sbb%");
        Question q = list.get(0);
        System.out.println(q.getTitle());
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("제목 수정 테스트")
    void testJpa6() {
        Optional<Question> oq = this.questionRepository.findById(1);
        Assertions.assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setTitle("수정된 제목");
        this.questionRepository.save(q);
    }

    @Test
    @DisplayName("제목 삭제 테스트")
    void testJpa7() {
        assertEquals(2, this.questionRepository.findAll().size());
        Optional<Question> oq = this.questionRepository.findById(1);
        Assertions.assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.findAll().size());
    }

    @Test
    @DisplayName("질문과 답변 추가 테스트")
    void testJpa8() {
        Optional<Question> oq = this.questionRepository.findById(2);
        Assertions.assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네, 자동으로 생성됩니다.");
        a.setQuestion(q); // 어떤 질문의 답변인지 알기 위해서 Question 객체를 설정
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

    @Test
    @DisplayName("답변 데이터 조회하기")
    void testJpa9() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        Assertions.assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
    }

    @Test
    @DisplayName("질문으로 답변 찾기")
    @Transactional
    void testJpa10() {
        // LazyInitializationException 발생 -> @Transactional :메서드가 종료될 때까지 DB 세션이 유지된다.
        // 왜냐하면 QuestionRepository가 Question 객체를 조회하고 나면 DB 세션이 끊어지기 때문이다.
        // 지연(Lazy) 방식:데이터를 필요한 시점에 가져오는 방식. 이 방식은 DB 세션이 끊어지지 않는 한 사용할 수 있다.
        // <-> 즉시(Eager) 방식: 데이터를 미리 가져오는 방식. 객체를 조회할 때 미리 answer 리스트를 모두 가져오는 방식.

        // 이 문제는 테스트 코드에서만 발생한다.
        // 실제 서버에서 JPA 프로그램들을 실행할 때는 DB 세션이 종료되지 않아 이와 같은 오류가 발생하지 않는다.
        Optional<Question> oq = this.questionRepository.findById(2);
        Assertions.assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();
        System.out.println(answerList.get(0).getContent());
        assertEquals(1, answerList.size());
    }

}
