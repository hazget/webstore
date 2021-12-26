package com.webstore.onlinestore.service;

import com.webstore.onlinestore.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductEntity> findAll();

    ProductEntity findById(Integer id);
}
