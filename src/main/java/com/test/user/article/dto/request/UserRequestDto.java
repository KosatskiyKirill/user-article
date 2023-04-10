package com.test.user.article.dto.request;

import java.util.List;

public class UserRequestDto {
    private Long id;
    private String name;
    private int age;
    private List<ArticleRequestDto> articles;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ArticleRequestDto> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleRequestDto> articles) {
        this.articles = articles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
