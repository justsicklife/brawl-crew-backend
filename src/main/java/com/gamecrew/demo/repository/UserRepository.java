package com.gamecrew.demo.repository;

import com.gamecrew.demo.domain.Brawler;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.domain.UserBrawler;
import com.gamecrew.demo.dto.BrawlersDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    public void save(User user, List<BrawlersDto> brawlersDto) {
        em.persist(user);

        for (BrawlersDto brawlerDto : brawlersDto) {
            // 유저 브롤러 생성
            UserBrawler userBrawler = new UserBrawler();
            // 브롤러 이름으로 조회
            Brawler brawler = brawlerRepository.findByBrawlerName(brawlerDto.getName());
            // 유저 브롤러 가 유저를 참조함 (유저 브롤러가 외래키 값을 가짐 유저에)
            userBrawler.setUser(user);
            // 유저 브롤러가 브롤러를 참조함
            userBrawler.setBrawler(brawler);
            // 유저 브롤러에 트로피 값을 가져옴
            userBrawler.setTrophy(brawlerDto.getTrophies());
            // 유저 브롤러 저장
            em.persist(userBrawler);
        }

    }
}
