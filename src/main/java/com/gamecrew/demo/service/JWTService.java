package com.gamecrew.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTService {

    private String secretKey = "";

    public JWTService() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey sk = keyGen.generateKey();

        secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    }

    public String generateToken(String username) {

        HashMap<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 60))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT에서 subject 필드(=username)를 추출
    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    // 토큰 안의 모든 클레임을 불러와서, 필요한 값만 꺼내는 재사용 가능한 메서드
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    // JWT에서 서명을 검증하면서 claims를 파싱해서 리턴
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //JWT의 subject(username)과 주어진 userDetails가 일치하는지 확인
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //토큰의 만료 날짜가 현재보다 이전이면 true
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //JWT 토큰의 만료 날짜(Date 객체) 추출하는 메서드
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
