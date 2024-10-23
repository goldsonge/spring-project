package com.estsoft.springproject.domain;

import com.estsoft.springproject.entity.Member;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    // 양방향 연관관계 -> @OneToMany (x) 성능이 안좋아서 잘 쓰진 않음.
    // 연관관계의 주인 명시
    @OneToMany(mappedBy = "team")   // n쪽의 FK 를 들고있는 아이를 적어주면 됨.
    List<Members> members = new ArrayList<>();
}
