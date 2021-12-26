package com.webstore.onlinestore.service;

import com.webstore.onlinestore.controller.dto.FinishPurchaseRequest;

public interface PurchaseService {

    Integer finishPurchase(FinishPurchaseRequest request);
}
