package com.gamecrew.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamecrew.demo.dto.PlayerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@Service
public class PlayerApiService {

    @Value("${brawlstars.api.playerUrl}")
    private String baseUrl;

    @Value("${brawlstars.api.token}")
    private String token; // 환경 변수나 설정 파일로 관리

    public PlayerDto getPlayerData(String playerTag) throws IOException {

        String formattedPlayerTag = formatPlayerTag(playerTag);
        URL url = new URL(baseUrl + formattedPlayerTag);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer response = new StringBuffer();
        String output;

        while((output = in.readLine()) != null) {
            response.append(output);
        }

        in.close();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.toString(),PlayerDto.class);
    }

    private String formatPlayerTag(String playerTag) {
        return playerTag.replace("#","%23");
    }

}
