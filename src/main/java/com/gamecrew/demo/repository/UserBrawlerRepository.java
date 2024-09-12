package com.gamecrew.demo.repository;

import com.gamecrew.demo.domain.UserBrawler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserBrawlerRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(UserBrawler userBrawler) {
        em.persist(userBrawler);
    }

}
