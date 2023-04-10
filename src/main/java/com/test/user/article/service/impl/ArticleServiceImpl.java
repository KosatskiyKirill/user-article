package com.test.user.article.service.impl;

import com.test.user.article.dao.ArticleDao;
import com.test.user.article.model.Article;
import com.test.user.article.service.ArticleService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleDao articleDao;

    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public Article save(Article article) {
        return articleDao.save(article);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleDao.findById(id);
    }
}
