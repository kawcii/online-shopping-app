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
        // 2 per category
        catalog.add(new Grocery("Rice", 25.00, "Grocery", "bag"));
        catalog.add(new Grocery("Chicken", 35.00, "Grocery", "kg"));

        catalog.add(new Electronics("Bluetooth Speaker", 120.00, "Electronics", 12));
        catalog.add(new Electronics("Extension Cable", 45.00, "Electronics", 6));

        catalog.add(new Household("Laundry Powder", 18.00, "Household", false));
        catalog.add(new Household("Storage Bucket", 22.00, "Household", true));

        catalog.add(new Clothing("Shoes", 85.00, "42"));
        catalog.add(new Clothing("Backpack", 65.00, "L"));
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
