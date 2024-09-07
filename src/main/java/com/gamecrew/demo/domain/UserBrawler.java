package com.gamecrew.demo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user_brawler")
public class UserBrawler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_brawler_id")
    private Long userBrawlerId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "brawler_id")
    private Brawler brawler;

    private int trophy;

}
