package com.gamecrew.demo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginInfoDto {
    private String userEmail;
    private String userPassword;
}
