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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final String url = "http://192.168.1.71:7010/mobile_saveUser";//istek atılacak url

    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_surname)
    EditText _surnameText;
    @BindView(R.id.input_mail)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {

            Toast.makeText(getApplicationContext(), "Bilgileri Kontrol Ediniz", Toast.LENGTH_LONG).show();
            _signupButton.setEnabled(true);
            return;
        }

        _signupButton.setEnabled(false);


        String name = _nameText.getText().toString();
        String surname = _surnameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        // TODO: Implement your own signup logic here.

        HashMap data = new HashMap();
        data.put("name", name);
        data.put("surname", surname);
        data.put("email", email);
        data.put("password", password);

        sendRequestSignUp(url, data);

    }

    public void onSignupSuccess() {
        Toast.makeText(getBaseContext(), "Kayıt Başarılı", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed(JSONObject response) throws JSONException {

        Toast.makeText(getBaseContext(), response.getString("message"), Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String surname = _surnameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("En az 3 karakter giriniz");
            valid = false;
        } else {
            _nameText.setError(null);
        }
        if (surname.isEmpty() || surname.length() < 3) {
            _surnameText.setError("En az 3 karakter giriniz");
            valid = false;
        } else {
            _surnameText.setError(null);
        }
        if (email.isEmpty()) {
            _emailText.setError("Geçerli bir mail adresi giriniz");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("En az 4 en fazla 10 karakterli bir şifre giriniz");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void sendRequestSignUp(String url, HashMap data) {

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Doğrulanıyor...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(this);  // this = context

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        try {
                            if (Boolean.parseBoolean(response.getString("success")) == true) {
                                progressDialog.dismiss();
                                onSignupSuccess();

                            } else {
                                progressDialog.dismiss();
                                onSignupFailed(response);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        queue.add(request);
    }
}
