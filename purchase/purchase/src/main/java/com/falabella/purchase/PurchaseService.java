package com.falabella.purchase;

import com.falabella.purchase.client.Client;
import com.falabella.purchase.client.ClientRepository;
import com.falabella.purchase.productClient.Product;
import com.falabella.purchase.productClient.ProductClient;
import com.falabella.purchase.purchaseProduct.PurchaseProduct;
import com.falabella.purchase.purchaseProduct.PurchaseProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    private final ClientRepository clientRepository;
    private final ProductClient productClient;


    @Autowired
    public PurchaseService(
            PurchaseRepository purchaseRepository,
            PurchaseProductRepository purchaseProductRepository,
            ClientRepository clientRepository,
            ProductClient productClient) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseProductRepository = purchaseProductRepository;
        this.clientRepository = clientRepository;
        this.productClient = productClient;
    }

    @Transactional
    public Purchase createPurchase(Purchase purchase) {
        Client client = clientRepository.findById(purchase.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        purchase.setClient(client);
        purchase.setTotal(0);
        System.out.println("Id del cliente es--->");
        System.out.println(purchase.getClient().getId());
        System.out.println(purchase.getTotal());

        purchase=purchaseRepository.save(purchase);
        System.out.println("Se guardo la compra del cliente es--->"+purchase);

            double totalCompra = 0;

            for (PurchaseProduct purchaseProduct : purchase.getPurchaseProducts()) {
                Product product = productClient.getProduct(purchaseProduct.getProductId());
                if (product.getStock() < purchaseProduct.getQuantity()) {
                    throw new RuntimeException("Stock out of stock" + product.getName());
                }

                double subTotal = product.getPrice() * purchaseProduct.getQuantity();
                totalCompra += subTotal;

                purchaseProduct.setPurchase(purchase);
                purchaseProduct.setUnit_price(product.getPrice());
                purchaseProduct.setSubtotal(subTotal);

                System.out.println("subtotal");
                System.out.println(purchaseProduct.getSubtotal());

                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String json = objectMapper.writeValueAsString(purchaseProduct);
                    System.out.println("JSON purchaseProduct---: " + json);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                purchaseProductRepository.save(purchaseProduct);
            }

        purchase.setTotal(totalCompra);

        return purchaseRepository.save(purchase);
    }
}
