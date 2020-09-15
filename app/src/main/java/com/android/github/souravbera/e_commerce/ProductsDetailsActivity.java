package com.android.github.souravbera.e_commerce;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductsDetailsActivity extends AppCompatActivity {

    private FloatingActionButton addToCart;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice, productDescription, productName;
    
    private String productId= "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_details);
        
        productId = getIntent().getStringExtra("pid");
        
        
        
        addToCart= findViewById(R.id.add_product_to_cart_btn);
        numberButton= findViewById(R.id.number_btn);
        productImage= findViewById(R.id.product_image);
        productDescription= findViewById(R.id.product_description);
        productPrice= findViewById(R.id.product_price);
        
        
        getProductDetails(productId);
    }

    private void getProductDetails(String productId) {
    }
}
