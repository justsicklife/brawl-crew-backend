package com.gamecrew.demo.dto.service;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PostWithBrawlerDto  {
    private Long postId;
    private String memo;
    private Long userId;
    private String username;
    private int userTrophyCount;
    private List<BrawlerDto> brawlers;  // Brawler 정보를 담을 리스트
}
