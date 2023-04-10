package com.test.user.article.service.mapper;

import com.test.user.article.dto.request.ArticleRequestDto;
import com.test.user.article.dto.response.ArticleResponseDto;
import com.test.user.article.model.Article;
import com.test.user.article.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    private final UserService userService;

    public ArticleMapper(UserService userService) {
        this.userService = userService;
    }

    public ArticleResponseDto toDto(Article article) {
        ArticleResponseDto articleDto = new ArticleResponseDto();
        articleDto.setId(article.getId());
        articleDto.setText(article.getText());
        articleDto.setColor(article.getColor());
        articleDto.setUserId(article.getUser().getId());
        return articleDto;
    }

    public Article toModel(ArticleRequestDto articleDto) {
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setText(articleDto.getText());
        article.setColor(articleDto.getColor());
        userService.findById(articleDto.getUserId()).ifPresent(article::setUser);
        return article;
    }
}
