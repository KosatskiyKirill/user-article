package com.test.user.article.configuration.security;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class JwtUserPayload {
    private String email;
    private List<String> roles;

    public JwtUserPayload() {
    }

    private JwtUserPayload(String email, List<String> roles) {
        this.email = email;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static JwtUserPayload of(User user) {

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toList());

        return new JwtUserPayload(user.getUsername(), roles);
    }
}
