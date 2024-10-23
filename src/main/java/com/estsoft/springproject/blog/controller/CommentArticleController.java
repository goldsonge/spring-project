package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springproject.blog.domain.dto.CommentResponseDTO;
import com.estsoft.springproject.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentArticleController {
    private final CommentService commentService;

    public CommentArticleController(CommentService commentService) {
        this.commentService = commentService;
    }

    //Post /api/article/{articleId}/comments. 댓글 작성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentResponseDTO> saveCommentByArticleId(@PathVariable Long articleId,
                                                                     @RequestBody CommentRequestDTO request) {
        Comment comment = commentService.saveComment(articleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentResponseDTO(comment));
    }

    //GET /api/comments/{commentsId} 작성하기. 댓글 단건 조회
    @GetMapping("/api/comments/{commentId}")
    // == public ResponseEntity<CommentRequestDTO> selectCommentById(@PathVariable Long commnetId) {
    public ResponseEntity<CommentResponseDTO> selectCommentById(@PathVariable("commentId") Long id) {
        Comment comment = commentService.findComment(id); // comment 타입
        return ResponseEntity.ok(new CommentResponseDTO(comment));
    }

    // PUT /api/comments/{{commentId}} 댓글 수정
    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateCommentById(@PathVariable Long commentId,
                                                                @RequestBody CommentRequestDTO request) {
        Comment updated = commentService.update(commentId, request);
        return ResponseEntity.ok(new CommentResponseDTO(updated));
    }

    //DELETE /api/comments/{{commentId}} 댓글 삭제
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok().build();
    }
}
