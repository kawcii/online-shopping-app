package com.example.online_shoppingapplication;

/**
 * Customer.java
 * Represents a customer placing an order.
 * Demonstrates: Encapsulation, Constructors, Methods
 */
public class Customer {

    private String name;
    private String phoneNumber;
    private String deliveryLocation;

    public Customer(String name, String phoneNumber, String deliveryLocation) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.deliveryLocation = deliveryLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public void displayCustomer() {
        System.out.println("Customer: " + name + " | Phone: " + phoneNumber
                + " | Delivery: " + deliveryLocation);
    }
}
