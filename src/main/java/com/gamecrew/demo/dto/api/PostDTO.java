package com.gamecrew.demo.dto.api;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class PostDTO {
    private Long postId;
    private String memo;
    private LocalDateTime createDate;
    private Long userId;

    private MostBrawlersDTO mostBrawlers;
    private UserDTO user;
}
