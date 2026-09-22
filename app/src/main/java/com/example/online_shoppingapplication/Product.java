package com.example.online_shoppingapplication;

/**
 * Product.java
 * Parent class for all product types in the supermarket shopping app.
 * Demonstrates: Encapsulation, Constructors, Methods
 * Author: Member 1 - Lead Java OOP & Backend Developer
 */
public class Product {

    protected String productName;
    protected double price;
    protected String category; // e.g. "Beverages", "Fresh Produce", "Stationery" - exact marketing category

    public Product(String productName, double price, String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public Product(String productName, double price) {
        this(productName, price, "General");
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Base method - overridden by each child class (polymorphism)
    public void displayProduct() {
        System.out.println(productName + " - K" + price);
    }
}
