package com.mysite.kws;

import com.mysite.kws.model.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EntityManagerTests {
    @PersistenceContext
    private EntityManager entityManager;

    public void test() {
        // 비영속 상태: 엔티티 매니저가 관리하지 않는 상태
        Question q1 = new Question();

        // 관리 상태: 엔티티 매니저가 관리하는 상태. 영속성 컨택스트에 저장
        entityManager.persist(q1);

        // 분리 상태: 엔티티 매니저가 관리하지 않는 상태. 영속성 컨택스트에서 관리 안함
        entityManager.detach(q1);

        // 제거된 상태: 엔티티를 영속성 컨텍스트와 데이터베이스에서 제거
        entityManager.remove(q1);
    }

}
