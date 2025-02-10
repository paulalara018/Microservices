package com.falabella.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository orderRepository;

    public List<Purchase> getAllPurchase() {
        return orderRepository.findAll();
    }

    public Purchase addPurchase(Purchase purchase) {
        return orderRepository.save(purchase);
    }

    public Purchase updatePurchase(Long id, Purchase purchase) {
        purchase.setId(id);
        return orderRepository.save(purchase);
    }

    public void deletePurchase(Long id) {
        orderRepository.deleteById(id);
    }
}
