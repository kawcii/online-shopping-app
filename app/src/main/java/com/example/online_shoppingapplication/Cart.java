package com.example.online_shoppingapplication;

import java.util.ArrayList;

/**
 * Cart.java
 * The functional shopping cart.
 * Demonstrates: ArrayList collection, Polymorphism (holds any Product subclass),
 * Methods (addProduct, removeProduct, calculateTotal)
 */
public class Cart {

    private ArrayList<Product> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    // Add any Product (or subclass - Grocery, Electronics, Clothing, Household)
    public void addProduct(Product product) {
        items.add(product);
        System.out.println("Added to cart: " + product.getProductName());
    }

    // Remove a product by matching name (simple approach for a prototype)
    public boolean removeProduct(String productName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductName().equalsIgnoreCase(productName)) {
                items.remove(i);
                System.out.println("Removed from cart: " + productName);
                return true;
            }
        }
        System.out.println("Product not found in cart: " + productName);
        return false;
    }

    // Calculate the total cost of everything in the cart
    public double calculateTotal() {
        double total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }

    // Display every item in the cart - loops through polymorphically,
    // calling each object's OWN overridden displayProduct()
    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("=== Shopping Cart ===");
        for (Product p : items) {
            p.displayProduct(); // polymorphism: calls the correct overridden version
        }
        System.out.println("Total: K" + calculateTotal());
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }
}
