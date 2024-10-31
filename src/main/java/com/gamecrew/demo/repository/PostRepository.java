package com.gamecrew.demo.repository;

import com.gamecrew.demo.domain.Brawler;
import com.gamecrew.demo.domain.Post;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.domain.UserBrawler;
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
public class PostRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void save(Post post) {
        em.persist(post);
    }

    public List<Post> findPostsWithUsers(int page, int size) {
        String queryStr = "SELECT p FROM Post p " +
                "JOIN FETCH p.user u";  // User만 fetch join

        TypedQuery<Post> query = em.createQuery(queryStr, Post.class);

        // 페이징 처리
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }


}
