package com.gamecrew.demo.repository;

import com.gamecrew.demo.domain.Brawler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class BrawlerRepository {

    @PersistenceContext
    EntityManager em;

    // JPQL을 사용한 조회
    public Brawler findByBrawlerName(String name) {
        // JPQL 쿼리 실행
        return em.createQuery("SELECT b FROM Brawler b WHERE b.name = :name", Brawler.class)
                .setParameter("name", name)  // 파라미터 설정
                .getSingleResult();  // 결과 반환

    }
}
