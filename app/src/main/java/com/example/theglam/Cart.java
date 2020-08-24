package com.example.theglam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.theglam.adapter.ListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Cart extends AppCompatActivity {
    int[] images = {R.drawable.prod1};
    FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseUser curUser;

    String[] version = {"Beauty product 1"};

    String[] versionNumber = {"12" };

    ListView lView;
    Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        ListAdapter lAdapter;
        auth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        curUser=auth.getCurrentUser();
        final String userid= String.valueOf(curUser.getUid());

        lView = (ListView) findViewById(R.id.androidList);
        order = (Button) findViewById(R.id.order);



        db.collection("Cart")
                .whereEqualTo("Userid", userid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("", document.getId() + " => " + document.getData());
                                System.out.println(document.getId() + " => " + document.getData());
                                String name= (String) document.getData().get("Name");
                                String description= (String) document.getData().get("Description");
                                String size= (String) document.getData().get("Size");
                                String price= (String) document.getData().get("Price");
                                int id= Integer.parseInt(document.getId());
                                String image= (String) document.getData().get("Image");
                                String detail_image= (String) document.getData().get("Detail_image");


                             //   getImage(id,image,name,description,size,price,productsList,subbrand,detail_image);

                            }

                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });






        lAdapter = new ListAdapter(Cart.this, version, versionNumber, images);

        lView.setAdapter(lAdapter);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ThankYou.class);
                startActivity(i);
               
            }
        });



    }
    }
