package com.fashionstore.model;

import java.sql.Timestamp;

public class Category {

    private int categoryId;
    private String name;
    private Timestamp createdAt;

    public Category() {

    }

    public Category(int categoryId, String name, Timestamp createdAt) {

        this.categoryId = categoryId;
        this.name = name;
        this.createdAt = createdAt;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}