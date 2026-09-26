package com.example.online_shoppingapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * CartActivity.java
 * Controls activity_cart.xml - shows the shopping cart and total.
 * Demonstrates: ArrayList collection, calculateTotal(), findViewById, OnClickListener
 *
 * NOTE: The XML only has ONE product row (not a repeating list), so this
 * screen shows the most recently added item as a representative preview.
 * The TOTAL shown is always accurate and includes EVERY item in the cart,
 * regardless of what's displayed in that single row.
 */
public class CartActivity extends AppCompatActivity {

    private TextView txtEmptyCart;
    private LinearLayout cartProductItem;
    private ImageView imgCartProduct;
    private TextView txtCartProductName, txtCartProductPrice, txtCartQuantity, txtCartSubtotal;
    private TextView txtCartTotal;
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        bindViews();
        displayCart();

        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh every time this screen is shown again, in case items were
        // added since the last time (e.g. customer went back to shop more)
        displayCart();
    }

    private void bindViews() {
        txtEmptyCart = findViewById(R.id.txtEmptyCart);
        cartProductItem = findViewById(R.id.cartProductItem);
        imgCartProduct = findViewById(R.id.imgCartProduct);
        txtCartProductName = findViewById(R.id.txtCartProductName);
        txtCartProductPrice = findViewById(R.id.txtCartProductPrice);
        txtCartQuantity = findViewById(R.id.txtCartQuantity);
        txtCartSubtotal = findViewById(R.id.txtCartSubtotal);
        txtCartTotal = findViewById(R.id.txtCartTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
    }

    private void displayCart() {
        Cart cart = CartManager.getCart();
        List<Product> items = cart.getItems();

        if (items.isEmpty()) {
            txtEmptyCart.setVisibility(View.VISIBLE);
            cartProductItem.setVisibility(View.GONE);
            txtCartTotal.setText("TOTAL: K0.00");
            btnCheckout.setEnabled(false);
            return;
        }

        txtEmptyCart.setVisibility(View.GONE);
        cartProductItem.setVisibility(View.VISIBLE);
        btnCheckout.setEnabled(true);

        // Show the most recently added product as the preview row
        Product lastAdded = items.get(items.size() - 1);

        // Quantity = how many times this exact product name appears in the cart
        int quantity = 0;
        for (Product p : items) {
            if (p.getProductName().equalsIgnoreCase(lastAdded.getProductName())) {
                quantity++;
            }
        }
        double subtotal = lastAdded.getPrice() * quantity;

        txtCartProductName.setText(items.size() > 1
                ? lastAdded.getProductName() + " (+" + (items.size() - 1) + " more item"
                  + (items.size() - 1 == 1 ? "" : "s") + ")"
                : lastAdded.getProductName());
        txtCartProductPrice.setText("Price: K" + lastAdded.getPrice());
        txtCartQuantity.setText("Quantity: " + quantity);
        txtCartSubtotal.setText("Subtotal: K" + subtotal);
        imgCartProduct.setImageResource(getImageForProduct(lastAdded.getProductName()));

        // This total is ALWAYS correct across every item, no matter how many
        // different products are in the cart - this is the real, accurate total
        txtCartTotal.setText("TOTAL: K" + cart.calculateTotal());
    }

    private int getImageForProduct(String productName) {
        switch (productName) {
            case "Rice": return R.drawable.product_rice;
            case "Chicken": return R.drawable.product_chicken;
            case "Bluetooth Speaker": return R.drawable.product_speaker;
            case "Extension Cable": return R.drawable.product_extension_cable;
            case "Laundry Powder": return R.drawable.product_laundry_powder;
            case "Storage Bucket": return R.drawable.product_storage_bucket;
            case "Shoes": return R.drawable.product_shoes;
            case "Backpack": return R.drawable.product_backpack;
            default: return R.drawable.product_rice;
        }
    }
}
