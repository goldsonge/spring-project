package com.estsoft.springproject.user.config;

import com.estsoft.springproject.user.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

// 스프링 시큐리티 설정
@Configuration
public class WebSecurityConfiguration {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 특정 요청은 스프링 시큐리티 설정을 타지 않도록 ignore
//    @Bean
//    public WebSecurityCustomizer ignore() {
//        return webSecurity -> webSecurity.ignoring()
//                .requestMatchers(toH2Console()) // /h2-console 시큐리티를 타지 않고 해당 페이지를 확인할 수 있음.
//                .requestMatchers("/static/**","/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html");
//    }

    // 특정 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
                // 아래 선언문 람다 표현식으로 수정
                // 3) 인증, 인가 설정
        return httpSecurity.authorizeHttpRequests(
                    custom -> custom.requestMatchers("/login", "/signup", "/user" ).permitAll()
                            //.requestMatchers("/articles/**").hasAuthority("ADMIN")       // ROLE_ADMIN
//                            .anyRequest().authenticated()
                            .anyRequest().permitAll()
                )
                // .requestMatchers("/login", "/signup", "/user").permitAll()      // 해당 루트는 인증 필요없이 허가
                // .anyRequest().authenticated()   // 앞에서 정의한 요청 외에는 인증 처리하겠다는 설정
                // .requestMatchers("/api/external").hasRole("admin")      // 해당 루트는 admin 권한이 있으면 인증. 인가 ("ROLE_admin")
                // 해당 구문까지 한 묶음.

                //4) 폼 기반 로그인 설정
                .formLogin(custom -> custom.loginPage("/login")
                        .defaultSuccessUrl("/articles", true))
                // .loginPage("/login")
                // .defaultSuccessUrl("/articles")     // 로그인 성공했을 경우 리디렉션 URL. 보이는 첫 페이지

                // 5) 로그아웃 설정
                .logout(custom -> custom.logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
                // .logoutSuccessUrl("/login")     // 로그아웃 성공했을 경우 리디렉션 URL.
                // .invalidateHttpSession(true)        // 로그아웃을 하면 해당 세션를 삭제 여부

                // 6) csrf 비활성화 - 활성화가 디폴트.
                .csrf(custom -> custom.disable())
                // .csrf().disable()
                .build();
    }

    // 7) 인증관리자 관련 설정
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) {
//        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)  // 8) 사용자 정보 서비스 설정
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }

    // 패스워드 암호화 방식 정의 (BCryptPasswordEncoder) 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
