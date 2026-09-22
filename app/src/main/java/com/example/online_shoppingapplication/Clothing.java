package com.example.online_shoppingapplication;

/**
 * Clothing.java
 * Covers: Apparel & Footwear
 * Demonstrates: Inheritance, Method Overriding
 */
public class Clothing extends Product {

    private String size; // e.g. S, M, L, XL, or shoe size

    public Clothing(String productName, double price, String size) {
        super(productName, price, "Apparel & Footwear");
        this.size = size;
    }

    public Clothing(String productName, double price) {
        super(productName, price, "Apparel & Footwear");
        this.size = "M";
    }

    public String getSize() {
        return size;
    }

    @Override
    public void displayProduct() {
        System.out.println("Clothing: " + getProductName() + " - K" + getPrice() + " (Size: " + size + ")");
    }
}