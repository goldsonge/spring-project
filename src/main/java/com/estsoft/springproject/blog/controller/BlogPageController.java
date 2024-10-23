package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleViewResponse;
import com.estsoft.springproject.blog.service.BlogService;
import com.estsoft.springproject.user.domain.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

// view를 보내주는 controller
@Controller
public class BlogPageController {
    private final BlogService blogService;
    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    String showArticle(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<Article> articleList = blogService.findAll();

        //== articleList.stream().map(article -> new ArticleViewResponse(article)).toList();
        List<ArticleViewResponse> list = articleList.stream()
                .map(ArticleViewResponse::new)
                .toList();

        //model.addAttribute() 객체 보내주기.
        model.addAttribute("articles", list);

        return "articleList";      // html 페이지명
    }

    // GET / articles/{id} 상세페이지 리턴
    @GetMapping("/articles/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Principal principal = (Principal) authentication.getPrincipal();
        //Users user = (Users) authentication.getPrincipal(); // @AuthenticationPrincipal Users users를 매개변수에 넣는 것과 같음.

        // 로직
        Article article = blogService.findBy(id);

        model.addAttribute("article",new ArticleViewResponse(article));

        return "article";   // article.html
    }

    // GET /new-articles?id=1
    // GET /new-articles
    @GetMapping("/new-articles")
    public String addArticle(@RequestParam(required = false) Long id, Model model) {
        if(id == null){     // 새로운 게시글 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else {            // 게시글 수정 : 기존 게시글 불러오기
            Article article = blogService.findBy(id);
            model.addAttribute("article",new ArticleViewResponse(article));
        }
        return "newArticle";    //newArticle.html
    }
}
