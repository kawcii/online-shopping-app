package com.example.online_shoppingapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageView imgProductDetail = findViewById(R.id.imgProductDetail);
        TextView txtProductDetailName = findViewById(R.id.txtProductDetailName);
        TextView txtProductDetailPrice = findViewById(R.id.txtProductDetailPrice);
        TextView txtProductDetailCategory = findViewById(R.id.txtProductDetailCategory);
        TextView txtProductDetailDescription = findViewById(R.id.txtProductDetailDescription);
        Button btnAddToCart = findViewById(R.id.btnAddToCart);

        String name = getIntent().getStringExtra("PRODUCT_NAME");
        double price = getIntent().getDoubleExtra("PRODUCT_PRICE", 0.0);
        String category = getIntent().getStringExtra("PRODUCT_CATEGORY");

        if (name == null) name = "Rice";
        if (category == null) category = "Grocery";

        txtProductDetailName.setText(name);
        txtProductDetailPrice.setText("Price: K" + String.format("%.2f", price));
        txtProductDetailCategory.setText("Category: " + category);
        txtProductDetailDescription.setText("High quality " + name.toLowerCase() + " from BIG V Supermarket inventory.");
        imgProductDetail.setImageResource(getImageForProduct(name));

        String finalName = name;
        double finalPrice = price;
        String finalCategory = category;

        btnAddToCart.setOnClickListener(v -> {
            Product product = new Product(finalName, finalPrice, finalCategory);
            CartManager.getCart().addProduct(product);
            Toast.makeText(this, "Added " + finalName + " to cart", Toast.LENGTH_SHORT).show();
        });
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
