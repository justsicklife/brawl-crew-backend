package com.gamecrew.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.dto.BrawlersDto;
import com.gamecrew.demo.dto.PlayerDto;
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
    final RestTemplate restTemplate;

    public void save(User user) {

        String baseUrl = "https://api.brawlstars.com/v1/players/";

        String playerTag = user.getPlayerTag();

        playerTag = playerTag.replace("#", "%23");

        ObjectMapper objectMapper = new ObjectMapper();

        try {


            URL url = new URL(baseUrl + playerTag);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjJlY2YxYzJjLTQyMGQtNDJjNS1hMWZlLTI1Mzk1MTU5ZmZhNiIsImlhdCI6MTcyNTg2NDk1OCwic3ViIjoiZGV2ZWxvcGVyL2JkNDM1MTM5LTcwYTQtNjJmZS01NmFiLTczNmZmMjZkOThlZCIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTE5LjIwNy4xODAuMTAzIl0sInR5cGUiOiJjbGllbnQifV19.OioilBK-XiqsIjicarpsvXQPbiBOtWDTAaGYACcxCrLbWtqXsTCKuFflVPbiuy4y7l4eCKUNmcKWijZ3TR6DUA";
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String output;

            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();

            PlayerDto player = objectMapper.readValue(response.toString(), PlayerDto.class);

            System.out.println("player.toString() = " + player.toString());

            List<BrawlersDto> brawlers = player.getBrawlers();

            List<BrawlersDto> list = brawlers.stream().sorted(Comparator.comparing(BrawlersDto::getTrophies).reversed()).limit(3).toList();

            userRepository.save(user,list);
            System.out.println("list = " + list);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
