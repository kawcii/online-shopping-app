package com.example.online_shoppingapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private TextView txtProductListTitle, txtNoProducts;
    private LinearLayout productItem1, productItem2, productItem3;
    private ImageView imgProduct1, imgProduct2, imgProduct3;
    private TextView txtProductName1, txtProductPrice1, txtProductCategory1;
    private TextView txtProductName2, txtProductPrice2, txtProductCategory2;
    private TextView txtProductName3, txtProductPrice3, txtProductCategory3;

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
            txtProductListTitle.setText(categoryFilter.toUpperCase() + " PRODUCTS");
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

        productItem1 = findViewById(R.id.productItem1);
        imgProduct1 = findViewById(R.id.imgProduct1);
        txtProductName1 = findViewById(R.id.txtProductName1);
        txtProductPrice1 = findViewById(R.id.txtProductPrice1);
        txtProductCategory1 = findViewById(R.id.txtProductCategory1);

        productItem2 = findViewById(R.id.productItem2);
        imgProduct2 = findViewById(R.id.imgProduct2);
        txtProductName2 = findViewById(R.id.txtProductName2);
        txtProductPrice2 = findViewById(R.id.txtProductPrice2);
        txtProductCategory2 = findViewById(R.id.txtProductCategory2);

        productItem3 = findViewById(R.id.productItem3);
        imgProduct3 = findViewById(R.id.imgProduct3);
        txtProductName3 = findViewById(R.id.txtProductName3);
        txtProductPrice3 = findViewById(R.id.txtProductPrice3);
        txtProductCategory3 = findViewById(R.id.txtProductCategory3);
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
        productItem1.setVisibility(View.GONE);
        productItem2.setVisibility(View.GONE);
        productItem3.setVisibility(View.GONE);
        txtNoProducts.setVisibility(View.GONE);

        if (filteredProducts.isEmpty()) {
            txtNoProducts.setVisibility(View.VISIBLE);
            return;
        }

        if (filteredProducts.size() > 0) {
            Product p1 = filteredProducts.get(0);
            productItem1.setVisibility(View.VISIBLE);
            txtProductName1.setText(p1.getProductName());
            txtProductPrice1.setText("Price: K" + String.format("%.2f", p1.getPrice()));
            txtProductCategory1.setText("Category: " + p1.getCategory());
            imgProduct1.setImageResource(getImageForProduct(p1.getProductName()));
            productItem1.setOnClickListener(v -> addProductToCart(p1));
        }

        if (filteredProducts.size() > 1) {
            Product p2 = filteredProducts.get(1);
            productItem2.setVisibility(View.VISIBLE);
            txtProductName2.setText(p2.getProductName());
            txtProductPrice2.setText("Price: K" + String.format("%.2f", p2.getPrice()));
            txtProductCategory2.setText("Category: " + p2.getCategory());
            imgProduct2.setImageResource(getImageForProduct(p2.getProductName()));
            productItem2.setOnClickListener(v -> addProductToCart(p2));
        }

        if (filteredProducts.size() > 2) {
            Product p3 = filteredProducts.get(2);
            productItem3.setVisibility(View.VISIBLE);
            txtProductName3.setText(p3.getProductName());
            txtProductPrice3.setText("Price: K" + String.format("%.2f", p3.getPrice()));
            txtProductCategory3.setText("Category: " + p3.getCategory());
            imgProduct3.setImageResource(getImageForProduct(p3.getProductName()));
            productItem3.setOnClickListener(v -> addProductToCart(p3));
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
