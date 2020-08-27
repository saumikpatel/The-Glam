package com.example.theglam.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class CartModel implements Parcelable {
    Integer productid;
    String productName;
    String productQty;
    String productPrice;
    private Uri uri;


    public CartModel(Integer productid, String productName, String productQty, String productPrice, Uri uri) {

        this.productid = productid;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.uri = uri;

    }

    protected CartModel(Parcel in) {
        if (in.readByte() == 0) {
            productid = null;
        } else {
            productid = in.readInt();
        }
        productName = in.readString();
        productQty = in.readString();
        productPrice = in.readString();
        uri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Parcelable.Creator<CartModel> CREATOR = new Parcelable.Creator<CartModel>() {
        @Override
        public CartModel createFromParcel(Parcel in) {
            return new CartModel(in);
        }

        @Override
        public CartModel[] newArray(int size) {
            return new CartModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (productid == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(productid);
        }
        parcel.writeString(productName);
        parcel.writeString(productQty);
        parcel.writeString(productPrice);
        parcel.writeParcelable(uri, i);
    }
}
