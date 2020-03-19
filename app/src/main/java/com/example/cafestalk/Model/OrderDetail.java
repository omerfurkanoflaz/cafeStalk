package com.example.cafestalk.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cafestalk.R;

import java.util.ArrayList;

public class OrderDetail implements Parcelable {
    private String name;
    private int image;


    public OrderDetail() {

    }

    public OrderDetail(String name, int image) {
        this.name = name;
        this.image = image;
    }

    protected OrderDetail(Parcel in) {
        name = in.readString();
        image = in.readInt();
    }

    public static final Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {
        @Override
        public OrderDetail createFromParcel(Parcel in) {
            return new OrderDetail(in);
        }

        @Override
        public OrderDetail[] newArray(int size) {
            return new OrderDetail[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static ArrayList<OrderDetail> getData() {
        ArrayList<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
        int orderImages[] = {R.drawable.food_image, R.drawable.food2, R.drawable.food3, R.drawable.food1, R.drawable.food2, R.drawable.food3,
                R.drawable.food1, R.drawable.food2, R.drawable.food3,
                R.drawable.food1, R.drawable.food2, R.drawable.food3};
        String[] orderName = {"detay1detay1detay1detay1detay1detay1detay1", "detay2", "detay", "detay4", "detay5", "detay6", "detay7", "detay8", "detay9", "detay10", "detay11", "detay12"};

        for (int i = 0; i < orderImages.length; i++) {
            OrderDetail temp = new OrderDetail();
            temp.setImage(orderImages[i]);
            temp.setName(orderName[i]);

            orderDetail.add(temp);

        }
        return orderDetail;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(name);
    }
}
