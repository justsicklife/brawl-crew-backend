package com.gamecrew.demo.repository;

import com.gamecrew.demo.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void save(Post post) {
        em.persist(post);
    }
}
