package com.webstore.onlinestore.service;

import com.webstore.onlinestore.controller.dto.FinishPurchaseRequest;
import com.webstore.onlinestore.entity.OrderEntity;
import com.webstore.onlinestore.entity.ProductEntity;
import com.webstore.onlinestore.entity.PurchaseItemEntity;
import com.webstore.onlinestore.entity.UserEntity;
import com.webstore.onlinestore.repository.OrderEntityRepository;
import com.webstore.onlinestore.repository.PurchaseItemEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final ProductService productService;
    private final UserService userService;
    private final OrderEntityRepository orderEntityRepository;
    private final PurchaseItemEntityRepository purchaseItemEntityRepository;

    @Override
    public Integer finishPurchase(FinishPurchaseRequest request) {
        log.info("creating order entity from request: {}", request);
        OrderEntity orderEntity = new OrderEntity();
        UserEntity userEntity = userService.findOrCreateUser(request.getUserName(), request.getUserSurname(),
                request.getPhone(), request.getEmail(), request.getAddress());
        orderEntity.setUserEntity(userEntity);
        orderEntity.setComment(request.getComment());
        orderEntity = orderEntityRepository.save(orderEntity);
        Map<Integer, Integer> productIdProductCount = getProductIdProductCountMap(request);

        for (Map.Entry<Integer, Integer> entry : productIdProductCount.entrySet()) {
            Integer k = entry.getKey();
            Integer v = entry.getValue();
            ProductEntity productEntity = productService.findById(k);
            PurchaseItemEntity p = new PurchaseItemEntity();
            p.setProductEntity(productEntity);
            p.setCount(v);
            p.setOrderEntity(orderEntity);
            purchaseItemEntityRepository.save(p);
        }

        if (request.getPassword() != null && request.getPassword().length() > 1) {
            userService.setPassword(userEntity.getId(), request.getPassword());
        }
        return orderEntity.getId();
    }

    private Map<Integer, Integer> getProductIdProductCountMap(FinishPurchaseRequest request) {
        Map<Integer, Integer> productIdProductCount = new HashMap<>();
        request.getProductIds().forEach(it -> {
            if (productIdProductCount.containsKey(it.getId())) {
                Integer productCount = productIdProductCount.get(it.getId());
                productCount = productCount + 1;
                productIdProductCount.put(it.getId(), productCount);
            } else {
                productIdProductCount.put(it.getId(), 1);
            }
        });
        return productIdProductCount;
    }
}
