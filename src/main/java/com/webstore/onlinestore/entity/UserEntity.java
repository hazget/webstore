package com.webstore.onlinestore.entity;

import com.webstore.onlinestore.entity.enums.UserStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Table(name = "user_table")
@Entity
@Data
@ToString(exclude = "password")
public class UserEntity extends BaseEntity {
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String password;
    @Column
    @Enumerated
    private UserRole role;
    @Column
    private String address;
    @Column
    @Enumerated
    private UserStatus status;
}
