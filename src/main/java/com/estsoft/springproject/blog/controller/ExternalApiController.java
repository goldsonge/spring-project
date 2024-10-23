package com.estsoft.springproject.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;


@Slf4j
@RestController
public class ExternalApiController {
    @GetMapping("/api/external")
    public String callApi() {
        //외부 API 호출
        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<String> json = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", String.class);
        // 결과 확인
        //log.info("StatusCode : {}",json.getStatusCode());
        //log.info(json.getBody());
        // 역직렬화 (json -> object)

        //외부 API 호출 후 역직렬화까지 (restTemplate)
        String url = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<List<Content>> resultList =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});

        log.info("code : {}", resultList.getStatusCode());
        log.info("{}", resultList.getBody());   // ["title :", "content :" ]
        return "ok";
    }
}
