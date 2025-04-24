package com.gamecrew.demo.repository;

import com.gamecrew.demo.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public User find(Long id) {
        return em.find(User.class, id);
    }

}
