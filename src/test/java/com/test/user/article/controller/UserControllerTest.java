package com.test.user.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.user.article.dto.response.UserResponseDto;
import com.test.user.article.model.User;
import com.test.user.article.service.UserService;
import com.test.user.article.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserMapper userMapper;
    @MockBean
    private UserService userService;
    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSignUp_Ok() throws Exception {
        User user1 = new User(1L, "Bob", 40, Collections.emptyList(),
                "bob@t.com", "123123123");
        User user2 = new User(1L, "Alice", 40, Collections.emptyList(),
                "alice@t.com", "123123123");
        List<User> users = Arrays.asList(user1, user2);
        List<UserResponseDto> result = users.stream()
                .map(userMapper::toDto)
                .collect(toList());

        when(userService.getAllUsersByAge(anyInt())).thenReturn(users);


        mockMvc.perform(MockMvcRequestBuilders.get("/users/ages/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(result)));
    }
}
