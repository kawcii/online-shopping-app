package com.example.online_shoppingapplication;

/**
 * Order.java
 * Represents a completed (simulated) order.
 * Demonstrates: Object composition (uses Customer and Cart objects), Methods
 */
public class Order {

    private Customer customer;
    private Cart cart;
    private double orderTotal;
    private boolean isCompleted;

    public Order(Customer customer, Cart cart) {
        this.customer = customer;
        this.cart = cart;
        this.orderTotal = cart.calculateTotal();
        this.isCompleted = false;
    }

    // Simulated checkout - this is a PROTOTYPE, no real payment is processed
    public void checkout() {
        System.out.println("\n=== ORDER SUMMARY (PROTOTYPE - NOT A REAL TRANSACTION) ===");
        customer.displayCustomer();
        cart.viewCart();
        System.out.println("Order Total: K" + orderTotal);
        isCompleted = true;
        System.out.println("Order placed successfully! (Simulated checkout only)");
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Cart getCart() {
        return cart;
    }
}
