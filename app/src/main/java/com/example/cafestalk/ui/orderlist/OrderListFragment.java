package com.example.cafestalk.ui.orderlist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafestalk.Adapter.OrderListAdapter;
import com.example.cafestalk.Common.ViewCart;
import com.example.cafestalk.MainActivity;
import com.example.cafestalk.Model.OrderList;
import com.example.cafestalk.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class OrderListFragment extends Fragment {

    RecyclerView recyclerView;
    FragmentManager manager;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ViewCart viewCart = new ViewCart();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("OrderListFragment", "oNaTTACH");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("OrderListFragment", "ONCREATE");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order_list, container, false);

        manager = MainActivity.mainactivity.getSupportFragmentManager();
        recyclerView = root.findViewById(R.id.recycler_order_list);
        OrderListAdapter orderListAdapter = new OrderListAdapter(MainActivity.mainactivity.getApplicationContext(), OrderList.getData());
        recyclerView.setAdapter(orderListAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.mainactivity.getApplicationContext(),
                2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        viewCart.onClick(root);

        return root;
    }

}
