package fr.upec.episen.tp8_stock.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_stocks")
public class ProductStock {
    @Id
    private String id;
    private String productId; // Référence vers le produit du tp8-server
    private int quantity;

    public ProductStock() {}

    public ProductStock(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters et Setters
    public String getId() { return id; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}