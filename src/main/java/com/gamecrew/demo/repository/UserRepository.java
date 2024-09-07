package com.gamecrew.demo.repository;

import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRepository {


    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = false)
    public void save(User user) {
        em.persist(user);
    }
}
