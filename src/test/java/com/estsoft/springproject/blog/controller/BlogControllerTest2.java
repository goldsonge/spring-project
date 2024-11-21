package com.estsoft.springproject.blog.controller;


import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BlogControllerTest2 {
    @InjectMocks
    BlogController blogController;

    @Mock
    BlogService blogService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    // 블로그 게시글 정보 저장 API 테스트
    @Test
    public void testSaveArticle() throws Exception {
        // given : API 호출에 필요한 데이터 만들기
        // {
        //  "title" : "mock_title",
        //  "content" : "mock_content"
        //  }
        // 직렬화 : 객체 -> json(String)
        String title = "mock_title";
        String content = "mock_content";
        AddArticleRequest request = new AddArticleRequest(title, content);
        ObjectMapper objectMapper = new ObjectMapper();
        String articleJson = objectMapper.writeValueAsString(request);

        // stub (service.saveArticle 호출시 위에서 만든 article 리턴되도록 stub 처리)
        Mockito.when(blogService.saveArticle(any()))
                .thenReturn(new Article(title, content));

        // when : API 호출
        ResultActions resultActions = mockMvc.perform(
                post("/api/articles")
                        .content(articleJson)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then : 호출 결과 검증
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("title").value(title))
                .andExpect(jsonPath("content").value(content));
    }
    // DELETE /articles/{id} : 블로그 게시글 삭제 API 테스트
    @Test
    public void testDelete() throws Exception {
        // given
        Long id = 1L;

        // when
        ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", id));

        // then : HTTP status code 검증, service.deleteBy 호출 횟수
        resultActions.andExpect(status().isOk());

        verify(blogService, times(1)).deleteBy(id);

    }

    // GET /articles/{id} : 블로그 게시글 단건 조회 API 테스트
    @Test
    public void testFindOne() throws Exception {
        // given
        Long id = 1L;
        String expectedTitle = "title";
        String expectedContent = "content";

        // stubing : article 객체를 만들어준다.
        Mockito.doReturn(new Article(expectedTitle, expectedContent))
                .when(blogService).findBy(id);

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", id));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("title").value(expectedTitle))
                .andExpect(jsonPath("content").value(expectedContent));

    }
}