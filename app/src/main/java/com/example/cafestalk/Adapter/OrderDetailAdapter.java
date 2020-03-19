package com.example.cafestalk.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafestalk.MainActivity;
import com.example.cafestalk.MenuDrawableActivity;
import com.example.cafestalk.Model.OrderDetail;
import com.example.cafestalk.Model.OrderList;
import com.example.cafestalk.R;
import com.example.cafestalk.ui.fooddetail.FoodDetailFragment;
import com.example.cafestalk.ui.orderdetail.OrderDetailFragment;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    ArrayList<OrderDetail> mOrderDetail;
    OrderDetail mFoodDetail;
    LayoutInflater layoutInflater;

    public OrderDetailAdapter(Context context, ArrayList<OrderDetail> orderDetails) {

        layoutInflater = LayoutInflater.from(MainActivity.mainactivity.getApplicationContext());
        this.mOrderDetail = orderDetails;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.content_order_detail, parent, false);
        OrderDetailAdapter.ViewHolder holder = new OrderDetailAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.ViewHolder holder, int position) {
        OrderDetail selectedOrderDetail = mOrderDetail.get(position);
        holder.setData(selectedOrderDetail, position);
        //her defasında find view by id çağırmayı enngeller  satıra tıklama olayında bir iş yaptrımak istersek
        holder.linearLayout.setTag(holder);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetailAdapter.ViewHolder holder = (OrderDetailAdapter.ViewHolder) v.getTag();//herbir satırdaki tag değerimi aldım holdera dönüştürdüm
                int position = holder.getPosition();//satır bilgisini yakaladım
                String p = Integer.toString(position);
                Toast.makeText(MainActivity.mainactivity, p, Toast.LENGTH_LONG).show();
                //String orderName = mOrderDetail.get(position).getName();
                ArrayList<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
                Bundle bundle = new Bundle();
                mFoodDetail = mOrderDetail.get(position);
                OrderDetail temp = new OrderDetail();
                temp.setImage(mFoodDetail.getImage());
                temp.setName(mFoodDetail.getName());
                orderDetail.add(temp);
                bundle.putParcelableArrayList("mFoodDetail", orderDetail);
                if (mFoodDetail != null) {
                    FoodDetailFragment foodDetailFragment = new FoodDetailFragment();
                    foodDetailFragment.setArguments(bundle);
                    MenuDrawableActivity.menuDrawableActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, foodDetailFragment, "fragment_food_detail")
                            .addToBackStack("fragment_food_detail").commit();
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return mOrderDetail.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout linearLayout;
        TextView orderDetailName;
        ImageView orderDetailImage;


        public ViewHolder(View itemView) {
            super(itemView);
            orderDetailName = itemView.findViewById(R.id.order_list_name);
            orderDetailImage = itemView.findViewById(R.id.order_list_image);
            linearLayout = itemView.findViewById(R.id.linear_layout);

        }

        public void setData(OrderDetail selectedOrderDetail, int position) {

            this.orderDetailName.setText(selectedOrderDetail.getName());
            this.orderDetailImage.setImageResource(selectedOrderDetail.getImage());

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.mainactivity.getApplicationContext(), "asdasd", Toast.LENGTH_LONG).show();
        }
    }

}
