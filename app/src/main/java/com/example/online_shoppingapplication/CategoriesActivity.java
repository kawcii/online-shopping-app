package com.example.online_shoppingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "CATEGORY_FILTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        LinearLayout categoryGrocery = findViewById(R.id.categoryGrocery);
        LinearLayout categoryElectronics = findViewById(R.id.categoryElectronics);
        LinearLayout categoryClothing = findViewById(R.id.categoryClothing);
        LinearLayout categoryHousehold = findViewById(R.id.categoryHousehold);

        categoryGrocery.setOnClickListener(v -> openProductList("Grocery"));
        categoryElectronics.setOnClickListener(v -> openProductList("Electronics"));
        categoryClothing.setOnClickListener(v -> openProductList("Clothing"));
        categoryHousehold.setOnClickListener(v -> openProductList("Household"));
    }

    private void openProductList(String categoryName) {
        Intent intent = new Intent(CategoriesActivity.this, ProductListActivity.class);
        intent.putExtra(EXTRA_CATEGORY, categoryName);
        startActivity(intent);
    }
}
