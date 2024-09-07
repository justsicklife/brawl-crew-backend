package com.gamecrew.demo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user_brawler")
public class UserBrawler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_brawler_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "brawler_id")
    private Brawler brawler;

    @Column(name = "rank")
    private int rank; // 선호 순위 등 추가 속성

    // 생성자 및 기타 메소드
    public UserBrawler(User user, Brawler brawler, int rank) {
        this.user = user;
        this.brawler = brawler;
        this.rank = rank;
    }
}
