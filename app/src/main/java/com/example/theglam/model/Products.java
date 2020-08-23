package com.example.theglam.model;

import android.net.Uri;

public class Products {

    Integer productid;
    String productName;
    String productQty;
    String productPrice;
    String description;
    private Uri uri;
    String category;

    public Products(Integer productid, String productName, String productQty, String productPrice, Uri uri, String description, String category) {

        this.productid = productid;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.uri = uri;
        this.description=description;
        this.category=category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return description;
    }
    public void setProductDescription(String description) {
        this.description = description;
    }

    public String getProductCategory() {
        return category;
    }
    public void setProductCategory(String category) {
        this.category = category;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Uri getImageUrl() {
        return uri;
    }

    public void setImageUrl(Uri imageUrl) {
        this.uri = uri;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }
}
