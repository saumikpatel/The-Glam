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
import android.widget.TextView;

import com.example.theglam.adapter.ProductAdapter;
import com.example.theglam.adapter.ProductCategoryAdapter;
import com.example.theglam.model.ProductCategory;
import com.example.theglam.model.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class Home extends AppCompatActivity {
    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;
    TextView hair, body, skin,face;
    Button cart;
    private FirebaseAuth auth;
    private FirebaseUser curUser;
    Object doc;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hair=(TextView) findViewById(R.id.hair);
        body=(TextView)findViewById(R.id.body);
        skin=(TextView)findViewById(R.id.skin);
        face=(TextView)findViewById(R.id.face);
        cart=(Button) findViewById(R.id.cart);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();


        List<ProductCategory> productCategoryList = new ArrayList<>();
        final List<Products> productsList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1, "Trending"));
        productCategoryList.add(new ProductCategory(2, "Most Popular"));
        productCategoryList.add(new ProductCategory(3, "All Body Products"));
        productCategoryList.add(new ProductCategory(4, "Skin Care"));
        productCategoryList.add(new ProductCategory(5, "Hair Care"));
        productCategoryList.add(new ProductCategory(6, "Make Up"));
        productCategoryList.add(new ProductCategory(7, "Fragrance"));
        setProductRecycler(productCategoryList);
        setdata(productsList,"hair");



        hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeitem(productsList);

                setdata(productsList,"hair");
            }
        });

        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeitem(productsList);
                setdata(productsList,"body");
            }
        });

        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeitem(productsList);
                setdata(productsList,"skin");


            }
        });

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeitem(productsList);
                setdata(productsList,"face");


            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),Cart.class);
                startActivity(i);
            }
        });

    }








    private void setdata(final List<Products> productsList, final String subbrand){



        db.collection("Products")
                .whereEqualTo("Category", subbrand)
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


                                        getImage(id,image,name,description,size,price,productsList);

                         }

                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

    private void removeitem(List<Products> productsList)
    {
        int size = productsList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                productsList.remove(0);
                productAdapter.notifyItemRemoved(i);
                productAdapter.notifyItemRangeChanged(i, productsList.size());
            }
        }
    }



    private void getImage(final int id,final String image, final String name, String description, final String size, final String price, final List<Products> productsList){
        Log.d("", description);
        FirebaseStorage storage = FirebaseStorage.getInstance();;

        StorageReference storageRef = storage.getReference();
        storageRef.child(image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               
                productsList.add(new Products(id,  name, size+" ml", "$ "+price,uri ));

                setProdItemRecycler(productsList);

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

    }

    private void setProductRecycler(List<ProductCategory> productCategoryList){

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);

    }

    private void setProdItemRecycler(List<Products> productsList){

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productsList);
        prodItemRecycler.setAdapter(productAdapter);


    }

}