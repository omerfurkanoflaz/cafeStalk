package com.example.cafestalk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public SessionManager session;
    TextView tvEmail;
    TextView tvPassword;
    String email, password;
    TextView txtHeaderMain;
    NavigationView navigationView;
    public static AppCompatActivity mainactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        startActivity(new Intent(getApplicationContext(), MenuDrawableActivity.class));
        mainactivity = this;


/*        tvEmail = findViewById(R.id.input_email);
        tvPassword = findViewById(R.id.input_password);
        session = new SessionManager(getApplicationContext());

        HashMap user = session.getUserDetails();

        email = (String) user.get(SessionManager.KEY_EMAIL);
        password = (String) user.get(SessionManager.KEY_SIFRE);*/

/*        if (email == null || password == null) {

            Toast.makeText(getApplicationContext(), "Lütfen Giriş Yapınız.", Toast.LENGTH_LONG).show();
         //  startActivity(new Intent(getApplicationContext(), LoginActivity.class));


        } else {
*//*            navigationView = findViewById(R.id.nav_view);
            View headerView = navigationView.getHeaderView(0);
            txtHeaderMain = (TextView) headerView.findViewById(R.id.header_main_id);
            txtHeaderMain.setText(email);*//*
        }*/


    }

}
