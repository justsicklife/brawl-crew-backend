package com.gamecrew.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.dto.BrawlersDto;
import com.gamecrew.demo.dto.PlayerDto;
import com.gamecrew.demo.repository.UserBrawlerRepository;
import com.gamecrew.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;

    final PlayerApiService playerApiService;

    final UserBrawlerRepository userBrawlerRepository;

    public void save(User user) {

        try {
            PlayerDto player = playerApiService.getPlayerData(user.getPlayerTag());

            if(player != null) {
                List<BrawlersDto> topBrawlers = getTopBrawlers(player);
                userRepository.save(user,topBrawlers);

                System.out.println("topBrawlers = " + topBrawlers);
            }
         } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<BrawlersDto> getTopBrawlers(PlayerDto player) {
        return player.getBrawlers().stream()
                .sorted(Comparator.comparing(BrawlersDto::getTrophies).reversed())
                .limit(3)
                .toList();
    }
}
