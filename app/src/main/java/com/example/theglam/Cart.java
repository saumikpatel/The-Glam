package com.example.theglam;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.theglam.adapter.ListAdapter;

public class Cart extends AppCompatActivity {
    int[] images = {R.drawable.prod1};

    String[] version = {"Beauty product 1"};

    String[] versionNumber = {"12" };

    ListView lView;
    Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        ListAdapter lAdapter;
        lView = (ListView) findViewById(R.id.androidList);
        order = (Button) findViewById(R.id.order);

        lAdapter = new ListAdapter(Cart.this, version, versionNumber, images);

        lView.setAdapter(lAdapter);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ThankYou.class);
                startActivity(i);
                finish();
            }
        });



    }
    }
