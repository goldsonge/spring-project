package com.estsoft.springproject.blog.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {
    @Test
    public void test() {
        Article article = new Article("제목","내용");

        // 객체가 많아지면 해당 체이닝으로 작성하는게 가독성이 좋음.
        // 빌더 패던 찾아보기.
        Article articleBuilder = Article.builder()
                .title("제목")
                .content("내용")
                .build();
    }
}