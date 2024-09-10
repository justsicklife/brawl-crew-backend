package com.gamecrew.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamecrew.demo.dto.Player;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BrawlStarsApiTest {

    @Test
    public void 브롤스타즈api테스트() throws JsonProcessingException {
        String baseUrl = "https://api.brawlstars.com/v1/players/";
        String tag = "#9QU209UYC";
        tag = tag.replace("#", "%23");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL(baseUrl + tag);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImM5MTMxNjJhLWVjYWYtNGIxOS05NmYwLTViNTc2ODVkZjYxNiIsImlhdCI6MTcyNTcwMzE3Miwic3ViIjoiZGV2ZWxvcGVyL2JkNDM1MTM5LTcwYTQtNjJmZS01NmFiLTczNmZmMjZkOThlZCIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTIyLjM3LjQ5LjIzIl0sInR5cGUiOiJjbGllbnQifV19.fk5j9GqycMVvhk2XIbjk8SaxXm4cPD7vipJ6qpt-hGwG7rYixH2fFquqt3ofluohfKL312eMxVwFx6g4kRZnFA";
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String output;

            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();

            Player player = objectMapper.readValue(response.toString(), Player.class);

            System.out.println("player.toString() = " + player.toString());

            System.out.println("player.getBrawlers().get(0).getName() = " + player.getBrawlers().get(0).getName());
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
