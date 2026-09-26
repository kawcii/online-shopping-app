package com.example.online_shoppingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        TextView txtConfirmationOrderSummary = findViewById(R.id.txtConfirmationOrderSummary);
        TextView txtConfirmationTotal = findViewById(R.id.txtConfirmationTotal);
        Button btnBackToHome = findViewById(R.id.btnBackToHome);

        String customerName = getIntent().getStringExtra("CUSTOMER_NAME");
        String phone = getIntent().getStringExtra("CUSTOMER_PHONE");
        String location = getIntent().getStringExtra("DELIVERY_LOCATION");
        double total = getIntent().getDoubleExtra("ORDER_TOTAL", 0.0);

        String summaryText = "Customer: " + (customerName != null ? customerName : "Valued Customer") +
                "\nPhone: " + (phone != null ? phone : "") +
                "\nDelivery Location: " + (location != null ? location : "") +
                "\nStatus: Order Confirmed (Simulated Prototype)";

        txtConfirmationOrderSummary.setText(summaryText);
        txtConfirmationTotal.setText("TOTAL: K" + String.format("%.2f", total));

        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(OrderConfirmationActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
