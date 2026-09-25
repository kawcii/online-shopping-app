package com.example.online_shoppingapplication;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * HomeActivity.java
 * Controls activity_home.xml - the main navigation screen.
 * Demonstrates: Java-Android UI Binding (findViewById, OnClickListener, Intent navigation)
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Grab the 3 buttons from the XML using their IDs
        Button btnViewProducts = findViewById(R.id.btnViewProducts);
        Button btnCategories = findViewById(R.id.btnCategories);
        Button btnShoppingCart = findViewById(R.id.btnShoppingCart);

        // VIEW PRODUCTS -> opens the Product List screen
        btnViewProducts.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProductListActivity.class);
            startActivity(intent);
        });

        // CATEGORIES -> opens the Categories screen
        btnCategories.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoriesActivity.class);
            startActivity(intent);
        });

        // SHOPPING CART -> opens the Cart screen
        btnShoppingCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
