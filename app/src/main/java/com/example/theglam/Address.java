package com.example.theglam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theglam.model.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.internal.Sleeper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Address extends AppCompatActivity {
    TextView uname,uapt,uaddress,ucity,upostal;
    Button btnNext;

    FirebaseFirestore db;
    private FirebaseUser curUser;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        uname = findViewById(R.id.name);
        uapt = findViewById(R.id.aptId);
        uaddress = findViewById(R.id.addressId);
        ucity = findViewById(R.id.city);
        upostal = findViewById(R.id.postalID);
        btnNext = findViewById(R.id.btnNext);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        curUser=auth.getCurrentUser();
        final Bundle b = getIntent().getExtras();
        final ArrayList<CartModel> list = b.getParcelableArrayList("list");



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = uname.getText().toString();
                String apt = uapt.getText().toString();
                String address = uaddress.getText().toString();
                String city = ucity.getText().toString();
                String postal = upostal.getText().toString();

                for (int i = 0; i < list.size(); i++) {
                    savedata(name,apt,address,city,postal,i,list);


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Intent i = new Intent(getApplicationContext(), Cart.class);
                startActivity(i);


            }
        });
    }

    private void savedata(String name, String apt, String address, String city, String postal, final int i, final ArrayList<CartModel> list) {
        final String userid= String.valueOf(curUser.getUid());
        Date date = new Date();
        Map<String, Object> data = new HashMap<>();
        data.put("Name", name);
        data.put("Apt", apt);
        data.put("Address", address);
        data.put("City", city);
        data.put("Postal", postal);
        data.put("Image",String.valueOf(list.get(i).getImageUrl()));
        data.put("Productid",list.get(i).getProductid());
        data.put("ProductName",list.get(i).getProductName());
        data.put("productQty",list.get(i).getProductQty());
        data.put("price",list.get(i).getProductPrice());
        data.put("Userid",userid);


        db.collection("Orders").document(String.valueOf(date))
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("", "DocumentSnapshot successfully written!");
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Added",
                                Toast.LENGTH_SHORT);
                        toast.show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("", "Error writing document", e);
                    }
                });
        Query productIdRef  =db.collection("Cart")
                .whereEqualTo("Productid", list.get(i).getProductid());

        productIdRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    document.getReference().delete();






//

                }

            }



//

        });



    }


        }


