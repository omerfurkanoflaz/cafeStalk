package com.example.cafestalk.Model;

import com.example.cafestalk.R;

import java.util.ArrayList;

public class OrderList {
    private String name;
    private int image;


    public OrderList() {

    }

    public OrderList(String name, int image) {
        this.name = name;
        this.image = image;
    }

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

    public static ArrayList<OrderList> getData() {
        ArrayList<OrderList> orderList = new ArrayList<OrderList>();
        int orderImages[] = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food1, R.drawable.food2, R.drawable.food3,
                R.drawable.food1, R.drawable.food2, R.drawable.food3,
                R.drawable.food1, R.drawable.food2, R.drawable.food3};
        String[] orderName = {"menu1", "menu2", "menu3", "menu4", "menu5", "menu6", "menu7", "menu8", "menu9", "menu10", "menu11", "menu12"};

        for (int i = 0; i < orderImages.length; i++) {
            OrderList temp = new OrderList();
            temp.setImage(orderImages[i]);
            temp.setName(orderName[i]);

            orderList.add(temp);

        }
        return orderList;

    }
}