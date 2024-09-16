package com.gamecrew.demo.domain;

import com.gamecrew.demo.domain.item.Rank;
import com.gamecrew.demo.domain.item.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brawler")
@Setter
@Getter
public class Brawler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brawler_id")
    private Long brawlerId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brawler", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBrawler> userBrawlers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Enumerated(EnumType.STRING)
    private Role role;

}
