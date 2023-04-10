package com.test.user.article.service;

import com.test.user.article.model.Article;
import java.util.Optional;

public interface ArticleService {
    Article save(Article article);

    Optional<Article> findById(Long id);
}
