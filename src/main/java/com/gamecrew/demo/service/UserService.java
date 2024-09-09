package com.gamecrew.demo.service;

import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;

@Service
@RequiredArgsConstructor
public class UserService {


    final UserRepository userRepository;
    final RestTemplate restTemplate;

    public void save(User user) {

        String playerTag = user.getPlayerTag();

        String apiUrl = "https://api.brawlstars.com/v1/brawlers/" + playerTag;

        HttpHeaders headers = new HttpHeaders();

        String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjJlY2YxYzJjLTQyMGQtNDJjNS1hMWZlLTI1Mzk1MTU5ZmZhNiIsImlhdCI6MTcyNTg2NDk1OCwic3ViIjoiZGV2ZWxvcGVyL2JkNDM1MTM5LTcwYTQtNjJmZS01NmFiLTczNmZmMjZkOThlZCIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTE5LjIwNy4xODAuMTAzIl0sInR5cGUiOiJjbGllbnQifV19.OioilBK-XiqsIjicarpsvXQPbiBOtWDTAaGYACcxCrLbWtqXsTCKuFflVPbiuy4y7l4eCKUNmcKWijZ3TR6DUA";

        headers.set("Authorization","Barer " + apiKey);

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, User.class);

        User body = response.getBody();

        userRepository.save(user);
    }
}
