package com.example.theglam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProductDetails extends AppCompatActivity {
ImageView back;
Button addtocart;
TextView description,price,quantity,name,category;
ImageView remove,add,productimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        back=findViewById(R.id.back);
        addtocart=findViewById(R.id.addtocart);
        description=findViewById(R.id.description);
        price=findViewById(R.id.price);
        quantity=findViewById(R.id.quantity);
        name=findViewById(R.id.name);
        category=findViewById(R.id.category);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
                finish();
            }
        });
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Cart.class);
                startActivity(i);

            }
        });

        Bundle b = getIntent().getExtras();


        String p_description = b.getString("Description");
        String p_price = b.getString("Price");
        String p_name = b.getString("Name");
        String p_category = b.getString("Category");

        description.setText(""+p_description);
        category.setText(""+ p_category);
        price.setText(""+ p_price);
        name.setText(""+ p_name);

    }
}