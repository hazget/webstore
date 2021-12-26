package com.webstore.onlinestore.service;

import com.webstore.onlinestore.entity.UserEntity;

public interface UserService {
    UserEntity findOrCreateUser(String name, String surname,
                                String phone, String email,
                                String address);
}
