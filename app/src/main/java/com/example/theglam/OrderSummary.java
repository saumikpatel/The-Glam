package com.example.theglam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderSummary extends AppCompatActivity {
TextView stotal,Tp;
Button btnCheckout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        stotal=findViewById(R.id.sTotal);
        Tp=findViewById(R.id.Tp);
        btnCheckout=findViewById(R.id.btnCheckout);
        final Bundle b = getIntent().getExtras();

        final double price = b.getDouble("price");

        stotal.setText(""+price +" $");

        double total = price;
        total =  total+((price*15)/100)+4;

        Tp.setText(""+total +" $");


        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Address.class);
                i.putExtras(b);
                startActivity(i);

            }
        });


    }
}