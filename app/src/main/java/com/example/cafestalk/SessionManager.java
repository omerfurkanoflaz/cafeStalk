package com.example.cafestalk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "SharedPreferencesOrnek";
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_EMAIL = "username";
    public static final String KEY_SIFRE = "password";
    public SessionManager(Context context){

        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String email, String sifre){

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_SIFRE, sifre);

        editor.commit();
    }

    public void checkLogin(){

        if(!this.isLoggedIn()){

            Intent i = new Intent(_context, MainActivity.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public HashMap getUserDetails(){

        HashMap user = new HashMap();

        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        user.put(KEY_SIFRE, pref.getString(KEY_SIFRE, null));

        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, MainActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }

    public boolean isLoggedIn(){

        return pref.getBoolean(IS_LOGIN, false);
    }
}
