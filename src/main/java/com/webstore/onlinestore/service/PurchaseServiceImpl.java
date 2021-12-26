package com.webstore.onlinestore.service;

import com.webstore.onlinestore.controller.dto.FinishPurchaseRequest;
import com.webstore.onlinestore.entity.OrderEntity;
import com.webstore.onlinestore.entity.ProductEntity;
import com.webstore.onlinestore.entity.PurchaseItemEntity;
import com.webstore.onlinestore.repository.OrderEntityRepository;
import com.webstore.onlinestore.repository.PurchaseItemEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        orderEntity.setUserEntity(userService.findOrCreateUser(request.getUserName(), request.getUserSurname(),
                request.getPhone(), request.getEmail(), request.getAddress()));
        orderEntity.setComment(request.getComment());
        orderEntity = orderEntityRepository.save(orderEntity);

        for (Map.Entry<Integer, Integer> entry : request.getProductIdProductCount().entrySet()) {
            Integer k = entry.getKey();
            Integer v = entry.getValue();
            ProductEntity productEntity = productService.findById(k);
            PurchaseItemEntity p = new PurchaseItemEntity();
            p.setProductEntity(productEntity);
            p.setCount(v);
            p.setOrderEntity(orderEntity);
            purchaseItemEntityRepository.save(p);
        }
        return orderEntity.getId();
    }
}
