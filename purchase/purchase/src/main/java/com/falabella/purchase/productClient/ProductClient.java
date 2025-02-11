package com.falabella.purchase.productClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//"http://localhost:8081"
@FeignClient(name="product-service",url="http://product:8080")
public interface ProductClient {
    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable String id);
}
