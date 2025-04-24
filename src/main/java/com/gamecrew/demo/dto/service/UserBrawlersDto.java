package com.gamecrew.demo.dto.service;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserBrawlersDto {
    private Long userId;
    private Long brawlerId;
    private Integer trophy;
}
