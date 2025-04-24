package com.gamecrew.demo.dto.api;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class UserDTO {

    private String playerTag;
    private String name;
    private int trophies;
    private String ageGroup;
    private String sex;
}
