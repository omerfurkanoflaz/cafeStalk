package com.example.cafestalk.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.cafestalk.MainActivity;
import com.example.cafestalk.MenuDrawableActivity;
import com.example.cafestalk.R;
import com.example.cafestalk.ui.order.OrderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {
    private DrawerLayout drawerLayout;
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        Log.e("HomeFragment", "oNaTTACH");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.e("HomeFragment", "ONCREATE");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        drawerLayout = MenuDrawableActivity.menuDrawableActivity.findViewById(R.id.drawer_layout);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mail ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        CardView card_view = (CardView) root.findViewById(R.id.link_view_order);
        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuDrawableActivity.menuDrawableActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, new OrderFragment(), "fragment_order")
                        .addToBackStack("fragment_order").commit();
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("HomeFragment", "onactivity created");
    }


    @Override

    public void onStart() {
        super.onStart();
        Log.e("HomeFragment", "on start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("HomeFragment", "onresume");
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("HomeFragment", "onpause");
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("HomeFragment", "onstop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("HomeFragment", "ondestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("HomeFragment", "ondestroy view");
    }


}
