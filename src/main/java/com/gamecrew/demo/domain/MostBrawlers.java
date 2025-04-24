package com.gamecrew.demo.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class MostBrawlers {
    private String firstBrawler;
    private int firstTrophy;
    private String secondBrawler;
    private int secondTrophy;
    private String thirdBrawler;
    private int thirdTrophy;
}
