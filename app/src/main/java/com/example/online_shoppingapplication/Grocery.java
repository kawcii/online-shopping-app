package com.example.online_shoppingapplication;

/**
 * Grocery.java
 * Covers: Packaged Groceries & Pantry, Beverages & Alcohol, Fresh & Perishable Foods
 */
public class Grocery extends Product {

    private String unitType; // e.g. "kg", "pack", "litre", "bottle"

    public Grocery(String productName, double price, String category, String unitType) {
        super(productName, price, category); // category e.g. "Beverages", "Fresh Produce", "Pantry"
        this.unitType = unitType;
    }

    public Grocery(String productName, double price, String category) {
        super(productName, price, category);
        this.unitType = "unit";
    }

    public String getUnitType() {
        return unitType;
    }

    @Override
    public void displayProduct() {
        System.out.println("Grocery [" + category + "]: " + productName
                + " - K" + price + " (" + unitType + ")");
    }
}
