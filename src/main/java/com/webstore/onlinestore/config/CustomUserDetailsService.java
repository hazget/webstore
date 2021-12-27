package com.webstore.onlinestore.config;

import com.webstore.onlinestore.entity.UserEntity;
import com.webstore.onlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findByEmail(email);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }
}
