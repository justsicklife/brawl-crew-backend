package com.gamecrew.demo.domain;

import com.gamecrew.demo.domain.item.AgeGroup;
import com.gamecrew.demo.domain.item.Sex;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "player_tag")
    private String playerTag;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_group")
    private AgeGroup ageGroup;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    private String memo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBrawler> userBrawlers = new ArrayList<>();

    // 편의 메소드 추가
    public void addBrawler(Brawler brawler, int rank) {
        UserBrawler userBrawler = new UserBrawler(this, brawler, rank);
        userBrawlers.add(userBrawler);
    }
}
