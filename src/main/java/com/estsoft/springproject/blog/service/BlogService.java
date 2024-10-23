package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {
    private final BlogRepository repository;  // 의존성 주입

    public BlogService(BlogRepository repository) {
        this.repository = repository;
    }
    //  blog 게시글 저장
    // repository.save(Article)
    public Article saveArticle(AddArticleRequest request){

        // 요청 txId: 1
        // log.warn("반드시 서버에서 확인해야하는 값 : {}", txId, 1);
        // Article entity = request.toEntity(); return repository.save(entity); 해당 구문으로 디버깅 하면 엔티티를 알아낼 수 있음.
        return repository.save(request.toEntity());
    }

    // blog 게시글 전체 조회
    public List<Article> findAll(){
     return repository.findAll();
    }

    // blog 게시글 단건 id(PK) 조회
    public Article findBy(Long id){
        // Optional.
        // repository.findById(id).orElse(new Article()); 이렇게 작성해도 괜찮음.
        // repository.findById(id).orElseGet(Article::new);
        // repository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found id: " + id));   // 전달받은 값의 아티클이 없을때 문구 노출
        // return repository.findById(id).get();
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found id :" + id));
    }

    // blog 게시글 삭제 (id)
    public void deleteBy(Long id){
        repository.deleteById(id);
    }

    // blog 게시글 수정
    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        // repository.findById(id); // 수정하고싶은 row (article객체) 가져옴
        Article article = findBy(id); // 수정하고싶은 row (article객체) 가져옴
        article.update(request.getTitle(), request.getContent());
        return article;
    }

}
