package com.example.cafestalk.ui.orderdetail;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafestalk.Adapter.OrderDetailAdapter;
import com.example.cafestalk.Common.ViewCart;
import com.example.cafestalk.MainActivity;
import com.example.cafestalk.Model.OrderDetail;
import com.example.cafestalk.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class OrderDetailFragment extends Fragment {

    RecyclerView recyclerView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ViewCart viewCart = new ViewCart();
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("OrderDetailFragment", "oNaTTACH");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("OrderDetailFragment", "ONCREATE");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order_detail, container, false);

        recyclerView = root.findViewById(R.id.recycler_order_detail);
        OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(MainActivity.mainactivity.getApplicationContext(), OrderDetail.getData());
        recyclerView.setAdapter(orderDetailAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.mainactivity.getApplicationContext(),
                1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        viewCart.onClick(root);

        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("OrderDetailFragment", "onstop");
        // collapsingToolbarLayout = SubOrderActivity.subOrderActivity.findViewById(R.id.collapsingToolbar_list);

        //  collapsingToolbarLayout.setTitle("Menu List");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("OrderFragment", "ondestroy");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("OrderDetailFragment", "ondestroy view");
    }
}
