package com.gamecrew.demo.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrawlersResponseDto {
    private Long id;
    private String name;
    private int trophies;
}
