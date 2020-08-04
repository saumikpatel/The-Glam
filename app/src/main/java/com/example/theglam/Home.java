package com.example.theglam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.theglam.adapter.ProductAdapter;
import com.example.theglam.adapter.ProductCategoryAdapter;
import com.example.theglam.model.ProductCategory;
import com.example.theglam.model.Products;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;
    TextView hair, body, skin,face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hair=(TextView) findViewById(R.id.hair);
        body=(TextView)findViewById(R.id.body);
        skin=(TextView)findViewById(R.id.skin);
        face=(TextView)findViewById(R.id.face);


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
        bodyproduct(productsList);


        hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeitem(productsList);
                hairproduct(productsList);
            }
        });
        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeitem(productsList);
                bodyproduct(productsList);
            }
        });
        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeitem(productsList);
                skinproduct(productsList);
            }
        });
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeitem(productsList);
                faceprodcut(productsList);
            }
        });

    }






    private void bodyproduct(List<Products> productsList){

        productsList.add(new Products(1, "Japanese Cherry Blossom", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "African Mango Shower Gel", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Japanese Cherry Blossom", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "African Mango Shower Gel", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Japanese Cherry Blossom", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "African Mango Shower Gel", "350 ml", "$ 25.00", R.drawable.prod1));

        setProdItemRecycler(productsList);
    }

    private void hairproduct(List<Products> productsList){

        productsList.add(new Products(1, "Hair product 1", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "Hair product 1", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Hair product 1", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "Hair product 1", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Hair product 1", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "Hair product 1", "350 ml", "$ 25.00", R.drawable.prod1));

        setProdItemRecycler(productsList);
    }
    private void skinproduct(List<Products> productsList){

        productsList.add(new Products(1, "Skin product 1", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "Skin product 1", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Skin product 1", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "Skin product 1", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Skin product 1", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "Skin product 1", "350 ml", "$ 25.00", R.drawable.prod1));

        setProdItemRecycler(productsList);
    }
    private void faceprodcut(List<Products> productsList){

        productsList.add(new Products(1, "Face product 1", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "Face product 1", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Face product 1", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "Face product 1", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Face product 1", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "Face product 1", "350 ml", "$ 25.00", R.drawable.prod1));

        setProdItemRecycler(productsList);
    }

    private void removeitem(List<Products> productsList)
    {
        int size = productsList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                productsList.remove(0);
            }
        }
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
        //removeitem(productsList);

    }

}