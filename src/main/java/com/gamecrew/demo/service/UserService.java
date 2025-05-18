package com.gamecrew.demo.service;

import com.gamecrew.demo.domain.MostBrawlers;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.dto.api.BrawlersResponseDto;
import com.gamecrew.demo.dto.api.PlayerResponseDto;
import com.gamecrew.demo.dto.request.LoginInfoDto;
import com.gamecrew.demo.repository.UserRepo;
import com.gamecrew.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    final UserRepository userRepository;

    final PlayerApiService playerApiService;

    final AuthenticationManager authManager;

    final JWTService jwtService;
    private final UserRepo userRepo;

    public void SaveUser(User user) {

        try {

            // api json 으로 받아옴
            PlayerResponseDto playerResponseData = playerApiService.getPlayerResponseData(user.getPlayerTag());

            if(playerResponseData != null) {
                // 가장 트로피가 많은 브롤러 3 개를 뽑아옴
                List<BrawlersResponseDto> topBrawlers = getTopBrawlers(playerResponseData);

                // api 로 조회한 값들 setting
                user.setTrophies(playerResponseData.getTrophies());
                user.setName(playerResponseData.getName());

                user.setMostBrawlers(new MostBrawlers(
                        topBrawlers.get(0).getName(),
                        topBrawlers.get(0).getTrophies(),
                        topBrawlers.get(1).getName(),
                        topBrawlers.get(1).getTrophies(),
                        topBrawlers.get(2).getName(),
                        topBrawlers.get(2).getTrophies()
                        )
                );

                // 유저 저장
                userRepository.save(user);

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

    private List<BrawlersResponseDto> getTopBrawlers(PlayerResponseDto playerResponseDto) {
        return playerResponseDto.getBrawlers().stream()
                .sorted(Comparator.comparing(BrawlersResponseDto::getTrophies).reversed())
                .limit(3)
                .toList();
    }

    public String verify(LoginInfoDto loginInfoDto) {

        // 이 로직에서 UserDetailService 가 호출 된다.
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginInfoDto.getUserEmail(),loginInfoDto.getUserPassword())
                );

        if(authentication.isAuthenticated()){
            log.info(loginInfoDto.toString());
            Long userId = userRepo.findByUserEmail(loginInfoDto.getUserEmail()).getUserId();
            return jwtService.generateToken(userId,loginInfoDto.getUserEmail());
        }

        return "fail";
    }

}
