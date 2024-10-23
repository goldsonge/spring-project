package com.estsoft.springproject.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public Users() {

    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    // 사용자의 권한 정보. 인가 정보!!
        //return List.of(new SimpleGrantedAuthority("user"));     // 새싹, 주니어, 중니어, 시니어, ...
    return List.of(new SimpleGrantedAuthority("user"), new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    // 계정의 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;    // false 처리하면 모든 로그인을 막을 수 있음.
        // 접속 날짜를 가지고 expired 하는 로직도 넣을 수 있음.
    }

    // 계정의 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정의 PW정보 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정의 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
