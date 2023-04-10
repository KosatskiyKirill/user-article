package com.test.user.article.service;

import com.test.user.article.model.User;
import com.test.user.article.model.enums.Color;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    List<User> getAllUsersByAge(int age);

    List<User> getAllUsersByColor(Color color);

    List<String> getUniqueNames();

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
