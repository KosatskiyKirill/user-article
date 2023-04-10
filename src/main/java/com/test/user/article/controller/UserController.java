package com.test.user.article.controller;

import com.test.user.article.dto.request.UserRequestDto;
import com.test.user.article.dto.response.UserResponseDto;
import com.test.user.article.model.User;
import com.test.user.article.model.enums.Color;
import com.test.user.article.service.UserService;
import com.test.user.article.service.mapper.UserMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userDto) {
        User user = userService.save(userMapper.toModel(userDto));
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @GetMapping("/ages/{userAge}")
    public ResponseEntity<List<UserResponseDto>> getUsersByCertainAge(@PathVariable int userAge) {
        List<UserResponseDto> users = userService.getAllUsersByAge(userAge).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/colors/{color}")
    public ResponseEntity<List<UserResponseDto>> getUsersWithCertainColor(@PathVariable
                                                                              Color color) {
        List<UserResponseDto> users = userService.getAllUsersByColor(color).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<String>> getUniqueUsersWithArticles() {
        return ResponseEntity.ok(userService.getUniqueNames());
    }
}
