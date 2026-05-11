package com.fashionstore.model;

import java.sql.Timestamp;

public class CartItem {

    private int cartItemId;
    private int cartId;
    private int variantId;
    private int quantity;
    private Timestamp createdAt;

    public CartItem() {

    }

    public CartItem(int cartItemId, int cartId, int variantId,
            int quantity, Timestamp createdAt) {

        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}