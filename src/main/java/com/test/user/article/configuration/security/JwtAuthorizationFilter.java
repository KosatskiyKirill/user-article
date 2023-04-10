package com.test.user.article.configuration.security;

import static com.test.user.article.configuration.security.SecurityConstants.ALLOW_TOKEN_ACCESS_HEADER;
import static com.test.user.article.configuration.security.SecurityConstants.HEADER_STRING;
import static com.test.user.article.configuration.security.SecurityConstants.SECRET;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.test.user.article.dto.response.UserResponseDto;
import com.test.user.article.model.User;
import com.test.user.article.service.UserService;
import com.test.user.article.service.mapper.UserMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserService userService,
                                  UserMapper userMapper, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userMapper = userMapper;
        this.encoder = encoder;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req,
            HttpServletResponse res) throws AuthenticationException {
        try {
            User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        addJwtToResponse(res, (UserDetails) auth.getPrincipal());
        addUserLoginToResponse(res, auth.getName());
    }

    private void addUserLoginToResponse(HttpServletResponse res, String email) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        UserResponseDto userRequestDto = userService.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Error during processing credentials."));

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        PrintWriter out = res.getWriter();
        out.print(mapper.writeValueAsString(userRequestDto));
        out.flush();
        out.close();
    }

    private void addJwtToResponse(HttpServletResponse res, UserDetails userDetails) {
        ObjectMapper mapper = new ObjectMapper();
        JwtUserPayload payload = JwtUserPayload.of((org
                .springframework
                .security
                .core
                .userdetails
                .User) userDetails);

        String token;
        try {
            token = Jwts.builder()
                    .setSubject(mapper.writeValueAsString(payload))
                    .setExpiration(new Date(2024, Calendar.JANUARY, 1))
                    .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                    .compact();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot deserialize JWTUserPayload");
        }
        res.addHeader(HEADER_STRING, token);
        res.addHeader(ALLOW_TOKEN_ACCESS_HEADER, HEADER_STRING);
    }
}
