package com.example.theglam.adapter;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theglam.Cart;
import com.example.theglam.ProductDetails;
import com.example.theglam.R;
import com.example.theglam.model.CartModel;
import com.example.theglam.model.Products;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    Context context;
    List<CartModel> productsList;

    public ListAdapter(Context context, List<CartModel> productsList){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.productsList = productsList;
    }



    @NonNull
    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_list_item, parent, false);
        return new ListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListViewHolder holder, int position) {
        Picasso.get().load(productsList.get(position).getImageUrl()).into(holder.prodImage);
        holder.prodName.setText(productsList.get(position).getProductName());
        holder.prodQty.setText("Quantity "+productsList.get(position).getProductQty());
        holder.prodPrice.setText("Price "+productsList.get(position).getProductPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static final class ListViewHolder extends RecyclerView.ViewHolder{

        ImageView prodImage;
        TextView prodName, prodQty, prodPrice;



        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            prodImage = itemView.findViewById(R.id.cimage);
            prodName = itemView.findViewById(R.id.cname);
            prodPrice = itemView.findViewById(R.id.cprice);
            prodQty = itemView.findViewById(R.id.cquantity);

        }
    }

//    @Override
//    public int getCount() {
//        return values.length;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return i;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//
//        ViewHolder viewHolder;
//
//        final View result;
//
//        if (convertView == null) {
//
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = LayoutInflater.from(context);
//            convertView = inflater.inflate(R.layout.single_list_item, parent, false);
//            viewHolder.txtName = (TextView) convertView.findViewById(R.id.aNametxt);
//            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.aVersiontxt);
//            viewHolder.icon = (ImageView) convertView.findViewById(R.id.appIconIV);
//
//            result=convertView;
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//            result=convertView;
//        }
//
//        viewHolder.txtName.setText(values[position]);
//        viewHolder.txtVersion.setText("Price: "+numbers[position] +"$");
//        viewHolder.icon.setImageResource(images[position]);
//
//        return convertView;
//    }
//
//    private static class ViewHolder {
//
//        TextView txtName;
//        TextView txtVersion;
//        ImageView icon;
//
//    }

}