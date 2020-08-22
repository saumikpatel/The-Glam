package com.example.theglam.model;

import android.net.Uri;

public class Products {

    Integer productid;
    String productName;
    String productQty;
    String productPrice;
    private Uri uri;

    public Products(Integer productid, String productName, String productQty, String productPrice, Uri uri) {

        this.productid = productid;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.uri = uri;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
