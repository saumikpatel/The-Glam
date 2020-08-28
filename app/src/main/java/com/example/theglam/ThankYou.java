package com.example.theglam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ThankYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        try {
            Thread.sleep(4000);
            Intent i = new Intent(getApplicationContext(),Home.class);
            startActivity(i);
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}