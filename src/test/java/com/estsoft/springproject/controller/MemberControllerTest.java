package com.estsoft.springproject.controller;

import com.estsoft.springproject.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest         // 임의 테스트 서버
@AutoConfigureMockMvc       // 해당 구문이 있어야 when절 진행 가능
class MemberControllerTest {
    @BeforeAll                    // 전체 테스트를 시작하기 전 1회 실행
    public static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach                    // 각각의 테스트 케이스를 실행하기 전마다 실행
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }
    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }
    @Autowired
    WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository repository;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetAllMember() throws Exception {    //메서드명을 한글로 쓸 순 있지만 영어로 쓰는걸 추천.
        // Assertions.assertEquals(); 을 assertEquals(); 로 생략가능.
        // given : 멤버 목록 저장 (생략)
        // when :  GET /members 호출 진행
        ResultActions resultActions = mockMvc.perform(get("/members")
                .accept(MediaType.APPLICATION_JSON));
        // then : 호출에 대한 응답 검증. response 검증
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
        ;

    }
    @AfterAll                        // 전체 테스트를 마치고 종료하기 전에 1회 실행
    public static void afterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach                        // 각각의 테스트 케이스를 종료하기 전마다 실행
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}