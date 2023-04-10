package com.test.user.article.dao;

import com.test.user.article.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Long> {
    List<User> findAllByAgeGreaterThan(int age);

    @Query(nativeQuery = true, value = "select * from users u "
            + "join articles a on u.id = a.user_id "
            + "where a.color = ?1")
    List<User> findAllByArticlesColor(String color);

    @Query(nativeQuery = true, value = "select u.name from users u "
            + "join articles a on u.id = a.user_id "
            + "group by u.name "
            + "having count(a.id) > 3")
    List<String> findAllByArticlesGreaterThanTree();

    Optional<User> findByEmail(String email);
}
