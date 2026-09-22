package com.example.online_shoppingapplication;

/**
 * Household.java
 * Covers: Household & Cleaning Supplies, Personal Care & Toiletries,
 *         Kitchenware & Home Linens, Stationery & Office Supplies
 *         Demonstrates: Inheritance, Method Overriding
 */
public class Household extends Product {

    private boolean fragile; // true for items like dinnerware, cookware, flasks

    public Household(String productName, double price, String category, boolean fragile) {
        super(productName, price, category); // category e.g. "Cleaning", "Personal Care", "Kitchenware", "Stationery"
        this.fragile = fragile;
    }

    public Household(String productName, double price, String category) {
        super(productName, price, category);
        this.fragile = false;
    }

    public boolean isFragile() {
        return fragile;
    }

    @Override
    public void displayProduct() {
        System.out.println("Household [" + getCategory() + "]: " + getProductName()
                + " - K" + getPrice() + (fragile ? " (Fragile)" : ""));
    }
}
