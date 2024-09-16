package com.gamecrew.demo.service;

import com.gamecrew.demo.domain.Brawler;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.domain.UserBrawler;
import com.gamecrew.demo.dto.BrawlersDto;
import com.gamecrew.demo.repository.BrawlerRepository;
import com.gamecrew.demo.repository.UserBrawlerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBrawlerService {

    final UserBrawlerRepository userBrawlerRepository;
    final BrawlerRepository brawlerRepository;

    @Transactional
    public void saveUserBrawlers(User user, List<BrawlersDto> brawlersDtos) {
        for (BrawlersDto brawlerDto : brawlersDtos) {
            UserBrawler userBrawler = createUserBrawler(user,brawlerDto);
            userBrawlerRepository.save(userBrawler);
        }
    }

    private UserBrawler createUserBrawler(User user, BrawlersDto brawlerDto) {
        UserBrawler userBrawler = new UserBrawler();
        Brawler brawler = brawlerRepository.findByBrawlerName(brawlerDto.getName());
        userBrawler.setUser(user);
        userBrawler.setBrawler(brawler);
        userBrawler.setTrophy(brawlerDto.getTrophies());
        return userBrawler;
    }



}
