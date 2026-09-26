package com.example.online_shoppingapplication;


/**
 * CartManager.java
 * Holds ONE shared Cart object that every screen can access.
 * Without this, each Activity would have its own separate, disconnected Cart,
 * and items added on the Product List screen would never show up on the Cart screen.
 */
public class CartManager {

    private static Cart sharedCart;

    // Returns the one shared Cart instance (creates it the first time it's needed)
    public static Cart getCart() {
        if (sharedCart == null) {
            sharedCart = new Cart();
        }
        return sharedCart;
    }
}
