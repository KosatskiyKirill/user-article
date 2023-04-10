package com.test.user.article.dto.request;

import com.test.user.article.model.enums.Color;
import javax.validation.constraints.NotNull;

public class ArticleRequestDto {
    private Long id;
    private String text;
    private Color color;
    private Long userId;

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
