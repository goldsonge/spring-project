package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.util.DateFormatUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//"id": 4,
//"body": "댓글 내용1",
//"createdAt": "2024-10-13 00:17:42",
//"article": {
//    "articleId": 12345,
//    "title": "게시글 제목",
//    "content": "게시글 내용",
//    "createdAt": "2024-10-13 12:00:00",
//    "updatedAt": "2024-10-13 12:00:00",
//}
@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private Long articleId;
    private String body;
    private String createdAt;
    private ArticleResponse article;

    public CommentResponseDTO(Comment comment) {
        Article articleFromComment = comment.getArticle();


        commentId = comment.getId();
        articleId = articleFromComment.getId();
        body = comment.getBody();
        createdAt = comment.getCreatedAt().format(DateFormatUtil.formatter);
        article = new ArticleResponse(articleFromComment);
    }
}
