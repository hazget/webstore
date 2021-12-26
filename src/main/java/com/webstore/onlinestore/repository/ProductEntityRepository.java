package com.webstore.onlinestore.repository;

import com.webstore.onlinestore.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
}
