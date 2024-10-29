package com.gamecrew.demo.service;

import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.dto.BrawlersResponseDto;
import com.gamecrew.demo.dto.PlayerResponseDto;
import com.gamecrew.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    final UserRepository userRepository;

    final PlayerApiService playerApiService;

    final UserBrawlerService userBrawlerService;

    public void saveUserAndTopBrawlers(User user) {

        try {

            // api json 으로 받아옴
            PlayerResponseDto playerResponseData = playerApiService.getPlayerResponseData(user.getPlayerTag());

            if(playerResponseData != null) {
                // 가장 트로피가 많은 브롤러 3 개를 뽑아옴
                List<BrawlersResponseDto> topBrawlers = getTopBrawlers(playerResponseData);

                // api 로 조회한 값들 setting
                user.setTrophies(playerResponseData.getTrophies());
                user.setName(playerResponseData.getName());

                // 유저 저장
                userRepository.save(user);

                // 유저 브롤러 저장
                userBrawlerService.saveUserBrawlers(user,topBrawlers);

                log.info("Top 3 brawlers saved for user: {}", user.getPlayerTag());
            } else {
                log.warn("Player not found for tag: {}", user.getPlayerTag());
            }
         } catch (Exception e) {
            log.error("Error while saving user and brawlers for tag: {}",user.getPlayerTag(),e);
            throw new RuntimeException("Failed to save user and brawler",e);
        }

    }

    public User userFindById(Long id) {
        return userRepository.find(id);
    }

    public List<User> getUsersWithBrawlers(int page,int size) {
        return userRepository.findUserWithBrawlers(page,size);
    }

    private List<BrawlersResponseDto> getTopBrawlers(PlayerResponseDto playerResponseDto) {
        return playerResponseDto.getBrawlers().stream()
                .sorted(Comparator.comparing(BrawlersResponseDto::getTrophies).reversed())
                .limit(3)
                .toList();
    }

}
