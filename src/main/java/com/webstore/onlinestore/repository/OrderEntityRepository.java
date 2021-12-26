package com.webstore.onlinestore.repository;

import com.webstore.onlinestore.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {
}
