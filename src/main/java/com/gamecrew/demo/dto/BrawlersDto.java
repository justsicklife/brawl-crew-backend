package com.gamecrew.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrawlersDto {
    private Long id;
    private String name;
    private int trophies;

}
