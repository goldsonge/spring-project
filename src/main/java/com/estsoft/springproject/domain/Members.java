package com.estsoft.springproject.domain;

import jakarta.persistence.*;

@Table(name = "Members")
@Entity
public class Members {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")   //FK
    private Team team;  // 일대다 다방향 연관관계 매핑

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
}
