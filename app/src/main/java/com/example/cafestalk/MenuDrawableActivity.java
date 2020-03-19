package com.example.cafestalk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.cafestalk.ui.home.HomeFragment;
import com.example.cafestalk.ui.order.OrderFragment;
import com.google.android.material.navigation.NavigationView;


public class MenuDrawableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    SessionManager session;
    public static AppCompatActivity menuDrawableActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuDrawableActivity = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.navigation_open_drawer, R.string.navigation_close_drawer);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, new HomeFragment(), "fragment_home").commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

       // session = new SessionManager(getApplicationContext());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, new HomeFragment(), "fragment_home")
                        .addToBackStack("fragment_home").commit();
                break;
            case R.id.nav_order:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, new OrderFragment(), "fragment_order")
                        .addToBackStack("fragment_order").commit();
                break;
            case R.id.nav_logout:
                session.logoutUser();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        Fragment fragment_home = getSupportFragmentManager().findFragmentByTag("fragment_home");
        Fragment fragment_order = getSupportFragmentManager().findFragmentByTag("fragment_order");

        if (fragment_order == null) {
            Log.d("MSG BACK BUTTON", "gERİ TUŞU ÇAĞIRILDI");
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
        } else {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

    }
}



