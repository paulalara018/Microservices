package com.falabella.purchase;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        System.out.println("voy a crear una compraaa------------>");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(purchase);
            System.out.println("JSON recibido: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return purchaseService.createPurchase(purchase);
    }


}
