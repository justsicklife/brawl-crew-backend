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

    final BrawlerRepository brawlerRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = false)
    public void save(User user) {
        em.persist(user);
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findUserWithBrawlers(int page, int size) {
        String queryStr = "SELECT u FROM User u " +
                "JOIN FETCH u.userBrawlers ub " +
                "JOIN FETCH ub.brawler";

        TypedQuery<User> query = em.createQuery(queryStr, User.class);

        // 페이징 처리
        query.setFirstResult(page*size);
        query.setMaxResults(size);

        return query.getResultList();
    }
}
