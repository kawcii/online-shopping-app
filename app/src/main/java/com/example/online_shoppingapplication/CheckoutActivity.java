package com.example.online_shoppingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private EditText edtCustomerName, edtCustomerPhone, edtDeliveryLocation;
    private TextView txtCheckoutOrderSummary, txtCheckoutTotal, txtCheckoutError;
    private Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        bindViews();
        displayOrderSummary();

        btnPlaceOrder.setOnClickListener(v -> placeOrder());
    }

    private void bindViews() {
        edtCustomerName = findViewById(R.id.edtCustomerName);
        edtCustomerPhone = findViewById(R.id.edtCustomerPhone);
        edtDeliveryLocation = findViewById(R.id.edtDeliveryLocation);
        txtCheckoutOrderSummary = findViewById(R.id.txtCheckoutOrderSummary);
        txtCheckoutTotal = findViewById(R.id.txtCheckoutTotal);
        txtCheckoutError = findViewById(R.id.txtCheckoutError);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
    }

    private void displayOrderSummary() {
        Cart cart = CartManager.getCart();
        List<Product> items = cart.getItems();

        StringBuilder summary = new StringBuilder();
        for (Product p : items) {
            summary.append(p.getProductName()).append(" - K").append(String.format("%.2f", p.getPrice())).append("\n");
        }
        if (items.isEmpty()) {
            summary.append("Cart is empty");
        }

        txtCheckoutOrderSummary.setText(summary.toString().trim());
        txtCheckoutTotal.setText("TOTAL: K" + String.format("%.2f", cart.calculateTotal()));
    }

    private void placeOrder() {
        String name = edtCustomerName.getText().toString().trim();
        String phone = edtCustomerPhone.getText().toString().trim();
        String location = edtDeliveryLocation.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || location.isEmpty()) {
            txtCheckoutError.setVisibility(View.VISIBLE);
            txtCheckoutError.setText("Please complete all required fields.");
            return;
        }

        Cart cart = CartManager.getCart();
        if (cart.getItems().isEmpty()) {
            txtCheckoutError.setVisibility(View.VISIBLE);
            txtCheckoutError.setText("Your cart is empty.");
            return;
        }

        txtCheckoutError.setVisibility(View.GONE);

        Customer customer = new Customer(name, phone, location);
        Order order = new Order(customer, cart);
        order.checkout();

        double total = cart.calculateTotal();

        // Clear cart after placing order
        cart.getItems().clear();

        Intent intent = new Intent(CheckoutActivity.this, OrderConfirmationActivity.class);
        intent.putExtra("CUSTOMER_NAME", name);
        intent.putExtra("CUSTOMER_PHONE", phone);
        intent.putExtra("DELIVERY_LOCATION", location);
        intent.putExtra("ORDER_TOTAL", total);
        startActivity(intent);
        finish();
    }
}
