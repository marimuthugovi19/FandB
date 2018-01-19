package test.fandb.class_files.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yellamobile-android on 19/01/18.
 */

public class ProductList implements Parcelable
{

    String productName="",currency="";
    int qty=0;
    Float productPrice;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static Creator<ProductList> getCREATOR() {
        return CREATOR;
    }

    protected ProductList(Parcel in) {
        productName = in.readString();
        productPrice = in.readFloat();

        qty = in.readInt();
        currency = in.readString();
    }

    public static final Creator<ProductList> CREATOR = new Creator<ProductList>() {
        @Override
        public ProductList createFromParcel(Parcel in) {
            return new ProductList(in);
        }

        @Override
        public ProductList[] newArray(int size) {
            return new ProductList[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public ProductList(String productName, Float productPrice, int qty,String currency) {
        this.productName=productName;
        this.productPrice=productPrice;
        this.qty=qty;
        this.currency=currency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeFloat(productPrice);
        dest.writeInt(qty);
        dest.writeString(currency);
    }
}
