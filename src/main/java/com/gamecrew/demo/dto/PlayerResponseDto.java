package com.gamecrew.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Json 파싱
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerResponseDto {

    private String tag;
    private String name;
    private int trophies;

    private List<BrawlersResponseDto> brawlers;
}
