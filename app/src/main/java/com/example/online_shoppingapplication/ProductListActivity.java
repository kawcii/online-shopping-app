package com.example.online_shoppingapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductListActivity extends AppCompatActivity {

    private TextView txtProductListTitle, txtNoProducts;
    private LinearLayout productListContainer;

    private List<Product> catalog;
    private List<Product> filteredProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        bindViews();
        buildCatalog();

        String categoryFilter = getIntent().getStringExtra(CategoriesActivity.EXTRA_CATEGORY);
        if (categoryFilter == null) {
            categoryFilter = getIntent().getStringExtra("CATEGORY_FILTER");
        }

        if (categoryFilter != null && !categoryFilter.isEmpty()) {
            txtProductListTitle.setText(categoryFilter.toUpperCase(Locale.getDefault()) + " PRODUCTS");
            filteredProducts = new ArrayList<>();
            for (Product p : catalog) {
                if (p.getCategory().equalsIgnoreCase(categoryFilter)) {
                    filteredProducts.add(p);
                }
            }
        } else {
            txtProductListTitle.setText("OUR PRODUCTS");
            filteredProducts = new ArrayList<>(catalog);
        }

        displayProducts();
    }

    private void bindViews() {
        txtProductListTitle = findViewById(R.id.txtProductListTitle);
        txtNoProducts = findViewById(R.id.txtNoProducts);
        productListContainer = findViewById(R.id.productListContainer);
    }

    private void buildCatalog() {
        catalog = new ArrayList<>();
        // Existing products
        catalog.add(new Grocery("Rice", 25.00, "Grocery", "bag"));
        catalog.add(new Grocery("Chicken", 35.00, "Grocery", "kg"));

        catalog.add(new Electronics("Bluetooth Speaker", 120.00, "Electronics", 12));
        catalog.add(new Electronics("Extension Cable", 45.00, "Electronics", 6));

        catalog.add(new Household("Laundry Powder", 18.00, "Household", false));
        catalog.add(new Household("Storage Bucket", 22.00, "Household", true));

        catalog.add(new Clothing("Shoes", 85.00, "42"));
        catalog.add(new Clothing("Backpack", 65.00, "L"));

        // New products added from drawable resources
        catalog.add(new Grocery("Apple", 12.50, "Grocery", "kg"));
        catalog.add(new Household("Bath Towel", 28.00, "Household", false));
        catalog.add(new Household("Bleach", 15.50, "Household", false));
        catalog.add(new Grocery("Capsicum", 14.00, "Grocery", "kg"));
        catalog.add(new Grocery("Instant Noodles", 2.50, "Grocery", "pack"));
        catalog.add(new Grocery("Large Tin Fish", 8.50, "Grocery", "tin"));
        catalog.add(new Grocery("White Rice", 22.50, "Grocery", "bag"));
        catalog.add(new Grocery("Orange", 10.00, "Grocery", "kg"));
        catalog.add(new Grocery("Prima Sausage", 12.50, "Grocery", "pack"));
        catalog.add(new Grocery("Small Tin Fish", 4.50, "Grocery", "tin"));
        catalog.add(new Clothing("Thongs", 18.00, "41"));
        catalog.add(new Household("Toilet Tissue", 12.00, "Household", false));
        catalog.add(new Grocery("Tomato", 11.50, "Grocery", "kg"));

        // Latest added product images
        catalog.add(new Clothing("Black Bag", 75.00, "One Size"));
        catalog.add(new Clothing("Blue Jeans", 65.00, "32"));
    }

    private void displayProducts() {
        productListContainer.removeAllViews();

        if (filteredProducts.isEmpty()) {
            txtNoProducts.setVisibility(View.VISIBLE);
            return;
        }

        txtNoProducts.setVisibility(View.GONE);
        float density = getResources().getDisplayMetrics().density;

        for (Product p : filteredProducts) {
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

            ImageView imgView = new ImageView(this);
            int imgSize = (int) (100 * density);
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(imgSize, imgSize);
            imgView.setLayoutParams(imgParams);
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgView.setImageResource(getImageForProduct(p.getProductName()));
            imgView.setContentDescription("Product image");

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
            nameView.setTextColor(getResources().getColor(R.color.bigv_brown, null));
            nameView.setTextSize(18f);
            nameView.setTypeface(null, Typeface.BOLD);

            TextView priceView = new TextView(this);
            priceView.setText("Price: K" + String.format(Locale.getDefault(), "%.2f", p.getPrice()));
            priceView.setTextColor(getResources().getColor(R.color.bigv_dark_text, null));
            priceView.setTextSize(16f);
            LinearLayout.LayoutParams priceParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            priceParams.setMargins(0, (int) (6 * density), 0, 0);
            priceView.setLayoutParams(priceParams);

            TextView catView = new TextView(this);
            catView.setText("Category: " + p.getCategory());
            catView.setTextColor(getResources().getColor(R.color.bigv_dark_text, null));
            catView.setTextSize(14f);
            LinearLayout.LayoutParams catParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            catParams.setMargins(0, (int) (4 * density), 0, 0);
            catView.setLayoutParams(catParams);

            textLayout.addView(nameView);
            textLayout.addView(priceView);
            textLayout.addView(catView);

            itemLayout.addView(imgView);
            itemLayout.addView(textLayout);

            itemLayout.setOnClickListener(v -> addProductToCart(p));

            productListContainer.addView(itemLayout);
        }
    }

    private void addProductToCart(Product product) {
        CartManager.getCart().addProduct(product);
        Toast.makeText(this, "Added " + product.getProductName() + " to cart", Toast.LENGTH_SHORT).show();
    }

    private int getImageForProduct(String productName) {
        switch (productName) {
            // Existing cases
            case "Rice": return R.drawable.product_rice;
            case "Chicken": return R.drawable.product_chicken;
            case "Bluetooth Speaker": return R.drawable.product_speaker;
            case "Extension Cable": return R.drawable.product_extension_cable;
            case "Laundry Powder": return R.drawable.product_laundry_powder;
            case "Storage Bucket": return R.drawable.product_storage_bucket;
            case "Shoes": return R.drawable.product_shoes;
            case "Backpack": return R.drawable.product_backpack;
            // New cases added for unreferenced product images
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
            // Latest added product images
            case "Black Bag": return R.drawable.product_black_bag;
            case "Blue Jeans": return R.drawable.product_blue_jeans;
            default: return R.drawable.product_rice;
        }
    }
}
