package com.gamecrew.demo.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class MostBrawlersDTO {
    private String firstBrawler;
    private int firstTrophy;
    private String secondBrawler;
    private int secondTrophy;
    private String thirdBrawler;
    private int thirdTrophy;
}
