package com.fashionstore.model;

import java.sql.Timestamp;

public class ProductVariant {

    private int variantId;
    private int productId;
    private String size;
    private String color;
    private double price;
    private int stockQuantity;
    private Timestamp createdAt;

    public ProductVariant() {

    }

    public ProductVariant(int variantId, int productId, String size,
            String color, double price, int stockQuantity,
            Timestamp createdAt) {

        this.variantId = variantId;
        this.productId = productId;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createdAt = createdAt;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}