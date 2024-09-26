package com.gamecrew.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_brawler")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBrawler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_brawler_id")
    private Long userBrawlerId;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brawler_id")
    private Brawler brawler;

    private int trophy;
}
