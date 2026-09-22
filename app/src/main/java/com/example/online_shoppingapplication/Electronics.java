package com.example.online_shoppingapplication;

/**
 * Electronics.java
 * Covers: Electronics & Audio, Hardware & Electrical
 */
public class Electronics extends Product {

    private int warrantyMonths; // use 0 for hardware items with no warranty

    public Electronics(String productName, double price, String category, int warrantyMonths) {
        super(productName, price, category); // category e.g. "Electronics & Audio", "Hardware & Electrical"
        this.warrantyMonths = warrantyMonths;
    }

    public Electronics(String productName, double price, String category) {
        super(productName, price, category);
        this.warrantyMonths = 0;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public void displayProduct() {
        System.out.println("Electronics [" + category + "]: " + productName
                + " - K" + price + " (Warranty: " + warrantyMonths + " months)");
    }
}