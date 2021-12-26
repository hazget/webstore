package com.webstore.onlinestore.repository;

import com.webstore.onlinestore.entity.PurchaseItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemEntityRepository extends JpaRepository<PurchaseItemEntity, Integer> {
}
