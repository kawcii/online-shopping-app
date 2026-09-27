package com.example.online_shoppingapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * CartActivity.java
 * Controls activity_cart.xml - shows all selected shopping cart items with their correct pictures, quantities, and accurate total.
 */
public class CartActivity extends AppCompatActivity {

    private TextView txtEmptyCart;
    private LinearLayout cartListContainer;
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
        displayCart();
    }

    private void bindViews() {
        txtEmptyCart = findViewById(R.id.txtEmptyCart);
        cartListContainer = findViewById(R.id.cartListContainer);
        txtCartTotal = findViewById(R.id.txtCartTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
    }

    private void displayCart() {
        Cart cart = CartManager.getCart();
        List<Product> items = cart.getItems();

        cartListContainer.removeAllViews();

        if (items.isEmpty()) {
            txtEmptyCart.setVisibility(View.VISIBLE);
            cartListContainer.setVisibility(View.GONE);
            txtCartTotal.setText("TOTAL: K0.00");
            btnCheckout.setEnabled(false);
            return;
        }

        txtEmptyCart.setVisibility(View.GONE);
        cartListContainer.setVisibility(View.VISIBLE);
        btnCheckout.setEnabled(true);

        // Group items by product name to count quantities and show each unique product with its image
        Map<String, ProductInfo> productMap = new LinkedHashMap<>();
        for (Product p : items) {
            String name = p.getProductName();
            if (productMap.containsKey(name)) {
                ProductInfo info = productMap.get(name);
                if (info != null) {
                    info.quantity++;
                }
            } else {
                productMap.put(name, new ProductInfo(p, 1));
            }
        }

        float density = getResources().getDisplayMetrics().density;

        for (Map.Entry<String, ProductInfo> entry : productMap.entrySet()) {
            ProductInfo info = entry.getValue();
            if (info == null) continue;
            Product p = info.product;
            int qty = info.quantity;
            double subtotal = p.getPrice() * qty;

            LinearLayout itemLayout = new LinearLayout(this);
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            itemParams.setMargins(0, 0, 0, (int) (16 * density));
            itemLayout.setLayoutParams(itemParams);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);
            itemLayout.setGravity(Gravity.CENTER_VERTICAL);
            itemLayout.setPadding(
                    (int) (12 * density),
                    (int) (12 * density),
                    (int) (12 * density),
                    (int) (12 * density)
            );
            itemLayout.setBackgroundColor(getResources().getColor(R.color.bigv_white, null));

            // Product Image
            ImageView imgView = new ImageView(this);
            int imgSize = (int) (100 * density);
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(imgSize, imgSize);
            imgView.setLayoutParams(imgParams);
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgView.setImageResource(getImageForProduct(p.getProductName()));
            imgView.setContentDescription("Cart product image");

            // Product Info
            LinearLayout textLayout = new LinearLayout(this);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
            );
            textParams.setMargins((int) (16 * density), 0, 0, 0);
            textLayout.setLayoutParams(textParams);
            textLayout.setOrientation(LinearLayout.VERTICAL);

            TextView nameView = new TextView(this);
            nameView.setText(p.getProductName());
            nameView.setTextColor(getResources().getColor(R.color.bigv_dark_text, null));
            nameView.setTextSize(18f);
            nameView.setTypeface(null, Typeface.BOLD);

            TextView priceView = new TextView(this);
            priceView.setText("Price: K" + String.format(Locale.getDefault(), "%.2f", p.getPrice()));
            priceView.setTextColor(getResources().getColor(R.color.bigv_brown, null));
            priceView.setTextSize(16f);
            LinearLayout.LayoutParams priceParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            priceParams.setMargins(0, (int) (6 * density), 0, 0);
            priceView.setLayoutParams(priceParams);

            TextView qtyView = new TextView(this);
            qtyView.setText("Quantity: " + qty);
            qtyView.setTextColor(getResources().getColor(R.color.bigv_dark_text, null));
            qtyView.setTextSize(16f);
            LinearLayout.LayoutParams qtyParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            qtyParams.setMargins(0, (int) (6 * density), 0, 0);
            qtyView.setLayoutParams(qtyParams);

            TextView subtotalView = new TextView(this);
            subtotalView.setText("Subtotal: K" + String.format(Locale.getDefault(), "%.2f", subtotal));
            subtotalView.setTextColor(getResources().getColor(R.color.bigv_dark_text, null));
            subtotalView.setTextSize(16f);
            subtotalView.setTypeface(null, Typeface.BOLD);
            LinearLayout.LayoutParams subtotalParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            subtotalParams.setMargins(0, (int) (6 * density), 0, 0);
            subtotalView.setLayoutParams(subtotalParams);

            textLayout.addView(nameView);
            textLayout.addView(priceView);
            textLayout.addView(qtyView);
            textLayout.addView(subtotalView);

            itemLayout.addView(imgView);
            itemLayout.addView(textLayout);

            cartListContainer.addView(itemLayout);
        }

        txtCartTotal.setText("TOTAL: K" + String.format(Locale.getDefault(), "%.2f", cart.calculateTotal()));
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
            case "Apple": return R.drawable.product_apple;
            case "Bath Towel": return R.drawable.product_bath_towel;
            case "Bleach": return R.drawable.product_bleach;
            case "Capsicum": return R.drawable.product_capsicum;
            case "Instant Noodles": return R.drawable.product_instant_noodles;
            case "Large Tin Fish": return R.drawable.product_large_tuna_tinfish;
            case "White Rice": return R.drawable.product_normal_rice;
            case "Orange": return R.drawable.product_orange;
            case "Prima Sausage": return R.drawable.product_prima_sausage;
            case "Small Tin Fish": return R.drawable.product_small_tuna_tinfish;
            case "Thongs": return R.drawable.product_thongs;
            case "Toilet Tissue": return R.drawable.product_toilet_tissue;
            case "Tomato": return R.drawable.product_tomato;
            case "Black Bag": return R.drawable.product_black_bag;
            case "Blue Jeans": return R.drawable.product_blue_jeans;
            default: return R.drawable.product_rice;
        }
    }

    private static class ProductInfo {
        Product product;
        int quantity;

        ProductInfo(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }
}
