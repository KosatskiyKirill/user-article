package com.test.user.article.service;

import com.test.user.article.dao.UserDao;
import com.test.user.article.model.User;
import com.test.user.article.model.enums.Color;
import com.test.user.article.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetAllUsersByAge_Ok() {
        User user1 = new User(1L, "Bob", 17, new ArrayList<>(),
                "bob@gmail.com", "12345");
        User user2 = new User(2L, "Alice", 18, new ArrayList<>(),
                "alice@gmail.com", "54321");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userDao.findAllByAgeGreaterThan(anyInt())).thenReturn(users);

        List<User> result = userService.getAllUsersByAge(10);

        assertEquals(users.size(), result.size());
        verify(userDao).findAllByAgeGreaterThan(anyInt());
    }
}
