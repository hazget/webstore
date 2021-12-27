package com.webstore.onlinestore.repository;

import com.webstore.onlinestore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
     UserEntity findByEmail(String email);
}
