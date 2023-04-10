package com.test.user.article.service.impl;

import com.test.user.article.dao.UserDao;
import com.test.user.article.model.User;
import com.test.user.article.model.enums.Color;
import com.test.user.article.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<User> getAllUsersByAge(int age) {
        return userDao.findAllByAgeGreaterThan(age);
    }

    @Override
    public List<User> getAllUsersByColor(Color color) {
        return userDao.findAllByArticlesColor(color.name());
    }

    @Override
    public List<String> getUniqueNames() {
        return userDao.findAllByArticlesGreaterThanTree();
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), new ArrayList<>());
    }
}
