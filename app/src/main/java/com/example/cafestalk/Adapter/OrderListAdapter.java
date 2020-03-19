package com.example.cafestalk.Adapter;

import android.app.Activity;
import android.content.Context;
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
import com.example.cafestalk.Model.OrderList;
import com.example.cafestalk.R;
import com.example.cafestalk.ui.order.OrderFragment;
import com.example.cafestalk.ui.orderdetail.OrderDetailFragment;
import com.example.cafestalk.ui.orderlist.OrderListFragment;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    ArrayList<OrderList> mOrderList;
    LayoutInflater layoutInflater;
    Context context;

    OrderListFragment orderListFragment = new OrderListFragment();

    public OrderListAdapter(Context context, ArrayList<OrderList> orderLists) {

        layoutInflater = LayoutInflater.from(MainActivity.mainactivity.getApplicationContext());
        this.mOrderList = orderLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.content_order_list, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderList selectedOrderList = mOrderList.get(position);
        holder.setData(selectedOrderList, position);
        //her defasında find view by id çağırmayı enngeller  satıra tıklama olayında bir iş yaptrımak istersek
        holder.linearLayout.setTag(holder);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder holder = (ViewHolder) v.getTag();//herbir satırdaki tag değerimi aldım holdera dönüştürdüm
                int position = holder.getPosition();//satır bilgisini yakaladım
                String p = Integer.toString(position);
                Toast.makeText(MainActivity.mainactivity, p, Toast.LENGTH_LONG).show();

                Activity activity = orderListFragment.getActivity();
                if(p!=null){
                    MenuDrawableActivity.menuDrawableActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_main, new OrderDetailFragment(), "fragment_order_detail")
                            .addToBackStack("fragment_order_detail").commit();
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout linearLayout;
        TextView orderListName;
        ImageView orderListImage;


        public ViewHolder(View itemView) {
            super(itemView);
            orderListName = itemView.findViewById(R.id.order_list_name);
            orderListImage = itemView.findViewById(R.id.order_list_image);
            linearLayout = itemView.findViewById(R.id.linear_layout);

        }

        public void setData(OrderList selectedOrderList, int position) {

            this.orderListName.setText(selectedOrderList.getName());
            this.orderListImage.setImageResource(selectedOrderList.getImage());

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.mainactivity.getApplicationContext(), "asdasd", Toast.LENGTH_LONG).show();
        }
    }

}
