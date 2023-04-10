package com.test.user.article.controller;

import com.test.user.article.dto.request.ArticleRequestDto;
import com.test.user.article.dto.response.ArticleResponseDto;
import com.test.user.article.model.Article;
import com.test.user.article.service.ArticleService;
import com.test.user.article.service.mapper.ArticleMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    public ArticleController(ArticleService articleService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
    }

    @PostMapping
    public ResponseEntity<ArticleResponseDto> saveArticle(@RequestBody
                                                              ArticleRequestDto articleDto) {
        Article article = articleService.save(articleMapper.toModel(articleDto));
        return ResponseEntity.ok(articleMapper.toDto(article));
    }
}
