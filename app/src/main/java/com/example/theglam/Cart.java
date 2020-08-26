package com.example.theglam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.theglam.adapter.ListAdapter;
import com.example.theglam.adapter.ProductAdapter;
import com.example.theglam.adapter.ProductCategoryAdapter;
import com.example.theglam.model.CartModel;
import com.example.theglam.model.ProductCategory;
import com.example.theglam.model.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Cart extends AppCompatActivity {

    FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseUser curUser;
    RecyclerView  cartItemRecycler;
    ListAdapter cartAdapter;





    Button order;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        final List<CartModel> productsList = new ArrayList<>();

        auth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        curUser=auth.getCurrentUser();
        final String userid= String.valueOf(curUser.getUid());


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
                                String quantity= String.valueOf(document.getData().get("Quantity"));
                                long id= (long) document.getData().get("Productid");
                                int cid=(int)id;
                                Log.d("", ""+id);

                                String image= (String) document.getData().get("Image");
                                String price= String.valueOf(document.getData().get("Price"));
                                Uri myUri = Uri.parse(image);
                                productsList.add(new CartModel(cid,  name, quantity, price,myUri));





                            }
                            setProdItemRecycler(productsList);



                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });


        order.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                double price=0;
                for(int i=0;i<productsList.size();i++){
                   price=price+ Double.parseDouble(productsList.get(i).getProductPrice());
                   System.out.println(price);




                }
                Intent intent = new Intent(getApplicationContext(),OrderSummary.class);
                Bundle b = new Bundle();








                b.putDouble("price", price);


                intent.putExtras(b);
                startActivity(intent);
            }
        });



    }





    public void setProdItemRecycler(List<CartModel> productsList){

        cartItemRecycler = findViewById(R.id.cart_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        cartItemRecycler.setLayoutManager(layoutManager);
        cartAdapter = new ListAdapter(this, productsList);
        cartItemRecycler.setAdapter(cartAdapter);


    }







}
