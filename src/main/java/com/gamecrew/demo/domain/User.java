package com.gamecrew.demo.domain;

import com.gamecrew.demo.domain.item.AgeGroup;
import com.gamecrew.demo.domain.item.Sex;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    private String memo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBrawler> userBrawlers = new ArrayList<>();
}
