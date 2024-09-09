package com.gamecrew.demo.repository;

import com.gamecrew.demo.domain.Brawler;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.domain.UserBrawler;
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

    final BrawlerRepository brawlerRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = false)
    public void save(User user) {
        em.persist(user);
        Brawler brawler = brawlerRepository.findByBrawlerName("Shelly");

        UserBrawler userBrawler = new UserBrawler();

        userBrawler.setUser(user);
        userBrawler.setBrawler(brawler);
        userBrawler.setTrophy(1000);

        em.persist(userBrawler);
    }
}
