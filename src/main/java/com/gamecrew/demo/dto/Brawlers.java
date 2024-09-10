package com.gamecrew.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Brawlers {
    private Long id;
    private String name;
    private Long trophies;

}
