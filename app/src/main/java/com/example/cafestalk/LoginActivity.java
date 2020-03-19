package com.example.cafestalk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final String url = "http://cafestalk.com/login";//istek atılacak url

    SessionManager session;

        @BindView(R.id.input_email)
        EditText _emailText;
        @BindView((R.id.input_password))
        EditText _passwordText;
        @BindView(R.id.btn_login)
        Button _loginButton;
        @BindView(R.id.link_signup)
        TextView _signupLink;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn() == true) {

            finish();
        }

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                // finish(); //kapattım çünkü hesap oluşturdan geri tuşuna basınca maine giriş yapıyordu
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();

            return;
        }
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        sendRequest(email, password);

        _loginButton.setEnabled(false);

    }

    @Override
    public void onBackPressed() {//login panelden geri basınca direkt çıkış
        Log.d("MSG BACK BUTTON", "gERİ TUŞU ÇAĞIRILDI");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

    }

    public void onLoginSuccess() {

        Log.d(TAG, "girildi1");
        _loginButton.setEnabled(true);
        /* finish();*/
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

    }

    public void onLoginFailed() {

        Toast.makeText(getBaseContext(), "Giriş Yapılamadı", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        if (email.isEmpty()) {

            _emailText.setError("Geçerli Bir Mail Adresi Giriniz");
            valid = false;

        } else {
            _emailText.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {

            _passwordText.setError("En az 4 en fazla 10 karakterli bir şifre giriniz");
            valid = false;
        }
        return valid;
    }

    public void sendRequest(final String email, final String password) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Doğrulanıyor...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strrequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject o = new JSONObject(response);
                            if (Boolean.parseBoolean(o.getString("success")) == false) {
                                Toast.makeText(getBaseContext(), "Giriş Yapılamadı request", Toast.LENGTH_LONG).show();
                                Log.d(TAG, "GİRİŞ BAŞARISIZ");
                                Log.d(TAG,response);
                                progressDialog.dismiss();
                                onLoginFailed();
                            } else {
                                Log.d(TAG,response);
                                session.createLoginSession(email, password);
                                Log.d(TAG, "GİRİŞ BAŞARILI");
                                Toast.makeText(getBaseContext(), "Hoşgeldiniz", Toast.LENGTH_LONG).show();
                                new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {
                                                progressDialog.dismiss();
                                                onLoginSuccess();

                                            }
                                        }, 500);
                            }
                        } catch (JSONException e) {
                            Log.d("Json_LoginActivity", String.valueOf(e));
                            progressDialog.dismiss();
                            onLoginFailed();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley_Error", String.valueOf(error));
                        progressDialog.dismiss();
                        onLoginFailed();
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("username", email);
                params.put("password", password);
                return params;
            }
        };
        queue.add(strrequest);

    }
}
