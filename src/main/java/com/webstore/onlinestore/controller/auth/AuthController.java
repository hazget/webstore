package com.webstore.onlinestore.controller.auth;

import com.webstore.onlinestore.config.jwt.JwtProvider;
import com.webstore.onlinestore.entity.UserEntity;
import com.webstore.onlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/auth")
    public AuthResponse auth(@Valid @RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getEmail());
        return new AuthResponse(token);
    }
}