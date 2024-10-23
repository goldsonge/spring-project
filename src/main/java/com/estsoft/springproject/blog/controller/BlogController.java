package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j      // logging을 위한 어노테이션
@RestController
@RequestMapping("/api")
@Tag(name = "블로그 저장/수정/삭제/조회용 API", description = "API 설명을 이곳에 작성하면 됩니다.")
public class BlogController {
    private final BlogService service;
    public BlogController(BlogService service) { this.service = service; }
    // RequestMapping (특정 url POST / articles)
    // @RequestMapping(method = RequestMethod.POST) == @PostMapping
    //@PostMapping("/articles")
    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> writeArticle(@RequestBody AddArticleRequest request){    // 넘겨줄때 각각에 맞게 파싱해줌.
        // System.out.println(request.getTitle());
        // System.out.println(request.getContent());
        // logging
        // logging의 5가지 레벨. 각각 출력되는 양이 다름.
        // trace, debug, info, warn, error   -> 개발자가 확인하는 용도로 사용해서 원격 저장소에 올릴땐 다 지우고 올리기
        // 만약 서버 내에서 무조건 확인해야하는 부분은 남겨놔야함.
        //log.debug("{}, {}", request.getTitle() ,request.getContent());
        //log.error("{}, {}", request.getTitle() ,request.getContent());
        log.info("{}, {}", request.getTitle() ,request.getContent());

//        try{
//
//        }catch (Exception e){
//            // log.error("",e); 에러메세지 무조건 나옴
//            // 재처리 해주어야함.
//        }
        Article article = service.saveArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article.convert());
        // return ResponseEntity.status(HttpStatus.CREATED).build();    // 성공 201
    }


    // Request Mepping      조회 : HTTP METHOD? GET
    @ApiResponses(value = {
            @ApiResponse(responseCode = "100", description = "요청에 성공했습니다.", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "블로그 전체 목록 보기", description = "블로그 메인 화면에서 보여주는 전체 목록")
    @GetMapping("/articles")  // articles/1 - 특정 아티클을 조회할 수 있도록도 할 수 있음
    public ResponseEntity<List<ArticleResponse>> findAll() {
        // List<Article> articleList = service.findAll();

        // List<Article> -> List<ArticleResponse> 변환해서 응답으로 보내기
        // 스트림 (map), for
        List<ArticleResponse> list = service.findAll().stream()
                // .map(article -> new ArticleResponse(article.getId(), article.getTitle(), article.getContent()))  // ArticleResponse 생성자를 이용해 변환
                .map(Article::convert)
                .toList();
        return ResponseEntity.ok(list);
    }

    // 단건 조회 API (Request mapping) 만들기      GET / article/1 , /article/{}
    @Parameter(name="id", description = "블로그 글 ID", example = "45")
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> findById(@PathVariable Long id) {
        Article article = service.findBy(id);   // Exception(5xx server error) -> 4xx Status Code
        // Article -> ArticleResponse
        return ResponseEntity.ok(article.convert());
    }

    // DELETE 메서드 사용 /articles/{id}
    // == @RequestMapping(method = RequestMethod.DELETE, value = "/articles/{id}")
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteBy(id);
        return ResponseEntity.ok().build();
    }

    // PUT /articles/{id} 수정 API body
    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> updateById(@PathVariable Long id,
                                                      @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = service.update(id, request);
        return ResponseEntity.ok(updatedArticle.convert());
    }

    // reference : https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-exceptionhandler.html
//    @ExceptionHandler(IllegalAccessError.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());  // reason : ""
//    }
}
