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
import androidx.cardview.widget.CardView;
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
        catalog.add(new Grocery("Trukai Jasmine Rice (10kg bag)", 54.00, "Grocery", "bag"));
        catalog.add(new Grocery("Chicken Breast (1kg tray)", 18.50, "Grocery", "kg"));

        catalog.add(new Electronics("Bluetooth Speaker", 120.00, "Electronics", 12));
        catalog.add(new Electronics("Extension Cord", 55.00, "Electronics", 6));

        catalog.add(new Household("Cold Powder (1kg)", 16.00, "Household", false));
        catalog.add(new Household("Plastic Bucket", 22.00, "Household", true));

        catalog.add(new Clothing("Puma Running Shoes", 110.00, "42"));
        catalog.add(new Clothing("Peak Sport Backpack", 55.00, "L"));

        // New products added from drawable resources
        catalog.add(new Grocery("Apple (1kg)", 10.00, "Grocery", "kg"));
        catalog.add(new Household("Bath Towel", 22.00, "Household", false));
        catalog.add(new Household("Dazzle Bleach (200ml)", 3.50, "Household", false));
        catalog.add(new Grocery("Capsicum (1kg)", 8.00, "Grocery", "kg"));
        catalog.add(new Grocery("Maggie Instant Noodles (85g)", 2.50, "Grocery", "pack"));
        catalog.add(new Grocery("Ocean blue Tuna/oil (425g)", 7.50, "Grocery", "tin"));
        catalog.add(new Grocery("Flame Jasmine Rice (500g)", 5.00, "Grocery", "bag"));
        catalog.add(new Grocery("Orange (1kg)", 10.00, "Grocery", "kg"));
        catalog.add(new Grocery("Prima Sausage (500g)", 12.50, "Grocery", "pack"));
        catalog.add(new Grocery("Ocean Blue Tuna/oil (180g)", 3.50, "Grocery", "tin"));
        catalog.add(new Clothing("Relaxo Thongs", 18.00, "13"));
        catalog.add(new Household("Value Toilet-Tissue (6 rolls)", 12.00, "Household", false));
        catalog.add(new Grocery("Tomato (1kg)", 7.50, "Grocery", "kg"));

        // Latest added product images
        catalog.add(new Clothing("Peak Sport Black Bag", 95.00, "One Size"));
        catalog.add(new Clothing("MA-Blue Jeans", 45.00, "32"));
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
            CardView cardView = new CardView(this);
            CardView.LayoutParams cardParams = new CardView.LayoutParams(
                    CardView.LayoutParams.MATCH_PARENT,
                    CardView.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(0, 0, 0, (int) (12 * density));
            cardView.setLayoutParams(cardParams);
            cardView.setRadius(12 * density);
            cardView.setCardElevation(4 * density);
            cardView.setUseCompatPadding(true);
            cardView.setCardBackgroundColor(getResources().getColor(R.color.bigv_white, null));

            LinearLayout itemLayout = new LinearLayout(this);
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            itemLayout.setLayoutParams(itemParams);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);
            itemLayout.setGravity(Gravity.CENTER_VERTICAL);
            itemLayout.setPadding(
                    (int) (12 * density),
                    (int) (12 * density),
                    (int) (12 * density),
                    (int) (12 * density)
            );

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

            cardView.addView(itemLayout);
            cardView.setOnClickListener(v -> addProductToCart(p));

            productListContainer.addView(cardView);
        }
    }

    private void addProductToCart(Product product) {
        CartManager.getCart().addProduct(product);
        Toast.makeText(this, "Added " + product.getProductName() + " to cart", Toast.LENGTH_SHORT).show();
    }

    private int getImageForProduct(String productName) {
        switch (productName) {
            case "Trukai Jasmine Rice (10kg bag)": return R.drawable.product_rice;
            case "Chicken Breast (1kg tray)": return R.drawable.product_chicken;
            case "Bluetooth Speaker": return R.drawable.product_speaker;
            case "Extension Cord": return R.drawable.product_extension_cable;
            case "Cold Powder (1kg)": return R.drawable.product_laundry_powder;
            case "Plastic Bucket": return R.drawable.product_storage_bucket;
            case "Puma Running Shoes": return R.drawable.product_shoes;
            case "Peak Sport Backpack": return R.drawable.product_backpack;
            case "Apple (1kg)": return R.drawable.product_apple;
            case "Bath Towel": return R.drawable.product_bath_towel;
            case "Dazzle Bleach (200ml)": return R.drawable.product_bleach;
            case "Capsicum (1kg)": return R.drawable.product_capsicum;
            case "Maggie Instant Noodles (85g)": return R.drawable.product_instant_noodles;
            case "Ocean blue Tuna/oil (425g)": return R.drawable.product_large_tuna_tinfish;
            case "Flame Jasmine Rice (500g)": return R.drawable.product_normal_rice;
            case "Orange (1kg)": return R.drawable.product_orange;
            case "Prima Sausage (500g)": return R.drawable.product_prima_sausage;
            case "Ocean Blue Tuna/oil (180g)": return R.drawable.product_small_tuna_tinfish;
            case "Relaxo Thongs": return R.drawable.product_thongs;
            case "Value Toilet-Tissue (6 rolls)": return R.drawable.product_toilet_tissue;
            case "Tomato (1kg)": return R.drawable.product_tomato;
            case "Peak Sport Black Bag": return R.drawable.product_black_bag;
            case "MA-Blue Jeans": return R.drawable.product_blue_jeans;
            default: return R.drawable.product_rice;
        }
    }
}
