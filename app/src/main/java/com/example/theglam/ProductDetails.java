package com.example.theglam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProductDetails extends AppCompatActivity {
ImageView back;
Button addtocart;
TextView description,price,quantity,name,category;
ImageView remove,add,productimage;
    FirebaseFirestore db;
    private FirebaseUser curUser;
    private FirebaseAuth auth;

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
        add=findViewById(R.id.add);
        remove=findViewById(R.id.remove);
        productimage=findViewById(R.id.productimage);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        final Bundle b = getIntent().getExtras();

        final int id = b.getInt("id");
        String p_description = b.getString("Description");
        final String p_price = b.getString("Price");
        final String p_name = b.getString("Name");
        String p_category = b.getString("Category");
        final String p_image= b.getString("Image");
        curUser=auth.getCurrentUser();
        final String userid= String.valueOf(curUser.getUid());


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
                int p_quantity = Integer.parseInt(quantity.getText().toString());
                double price= p_quantity*Integer.parseInt(p_price);
                Date date = new Date();


                Map<String, Object> data = new HashMap<>();
                data.put("Userid", userid);
                data.put("Quantity", p_quantity);
                data.put("Productid", id);
                data.put("Name", p_name);
                data.put("Price", price);
                data.put("Image",p_image);

                db.collection("Cart").document(String.valueOf(date))
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("", "DocumentSnapshot successfully written!");


                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Added",
                                        Toast.LENGTH_SHORT);
                                toast.show();
                                Intent i = new Intent(getApplicationContext(), Cart.class);
                                startActivity(i);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("", "Error writing document", e);
                            }
                        });

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p_quantity = Integer.parseInt(quantity.getText().toString());
                p_quantity=p_quantity+1;
                quantity.setText(""+p_quantity);

            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p_quantity = Integer.parseInt(quantity.getText().toString());
                if(p_quantity>0) {
                    p_quantity = p_quantity - 1;
                    quantity.setText("" + p_quantity);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Qunatity can not be negative",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });





        description.setText(""+p_description);
        category.setText(""+ p_category);
        price.setText(""+ p_price);
        name.setText(""+ p_name);

        Picasso
                .get()
                .load(p_image)
                .noFade()
                .into(productimage);

    }
}