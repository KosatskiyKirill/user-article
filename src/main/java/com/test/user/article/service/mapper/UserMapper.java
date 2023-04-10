package com.test.user.article.service.mapper;

import com.test.user.article.dto.request.UserRequestDto;
import com.test.user.article.dto.response.UserResponseDto;
import com.test.user.article.model.User;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ArticleMapper articleMapper;

    public UserResponseDto toDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setArticles(user.getArticles().stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList()));
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public User toModel(UserRequestDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setArticles(userDto.getArticles().stream()
                .map(articleMapper::toModel)
                .collect(Collectors.toList()));
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
