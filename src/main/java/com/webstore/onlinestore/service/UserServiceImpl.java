package com.webstore.onlinestore.service;

import com.webstore.onlinestore.entity.UserEntity;
import com.webstore.onlinestore.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.webstore.onlinestore.entity.UserRole.CUSTOMER;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserEntityRepository userEntityRepository;

    @Override
    public UserEntity findOrCreateUser(String name, String surname, String phone, String email, String address) {
        UserEntity userEntity = userEntityRepository.findByEmail(email);
        if (nonNull(userEntity)) {
            return userEntity;
        }
        userEntity = new UserEntity();
        userEntity.setRole(CUSTOMER.name());
        userEntity.setAddress(address);
        userEntity.setSurname(surname);
        userEntity.setName(name);
        userEntity.setPhone(phone);
        userEntity.setEmail(email);
        return userEntityRepository.save(userEntity);

    }
}
