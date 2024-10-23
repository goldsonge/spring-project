package com.estsoft.springproject.blog.domain;

import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;        // DB 테이블의 id와 컬럼 매칭

    @Column(nullable = false)       // null 불가.
    private String title;   // DB 테이블의 title와 컬럼 매칭

    @Column(nullable = false)
    private String content; // DB 테이블의 content와 컬럼 매칭

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

    // Article 생성자
    @Builder        // 롬복으로 빌더 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse convert(){
        return new ArticleResponse(id, title, content);
    }

    public void update(String title, String content){
        // if(!title.isBlank()){ this.title = title; }
        // if(!content.isBlank()){ this.content = content; }
            this.title = title;
            this.content = content;
    }

}
