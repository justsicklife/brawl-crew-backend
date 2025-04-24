package com.gamecrew.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gamecrew.demo.domain.item.AgeGroup;
import com.gamecrew.demo.domain.item.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EntityListeners(AuditingEntityListener.class) // 추가
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

        @CreatedDate
        @Column(name = "create_date")
        private LocalDateTime createDate;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private int trophies;

        @Embedded
        MostBrawlers mostBrawlers;

        @JsonIgnore
        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private Post post;
    }
