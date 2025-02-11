package com.falabella.purchase;


import com.falabella.purchase.client.Client;
import com.falabella.purchase.purchaseProduct.PurchaseProduct;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="client_id",nullable = false)
    private Client client;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PurchaseProduct> purchaseProducts = new ArrayList<>();

    private double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<PurchaseProduct> getPurchaseProducts() {
        return purchaseProducts;
    }

    public void setPurchaseProducts(List<PurchaseProduct> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }


}

