package com.gamecrew.demo.dto.service;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BrawlerDto {
    private Long brawlerId;
    private String brawlerName;
    private int trophyCount;
}
