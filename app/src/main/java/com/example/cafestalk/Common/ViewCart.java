package com.example.cafestalk.Common;

import android.view.View;

import com.example.cafestalk.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ViewCart implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        FloatingActionButton cart = v.findViewById(R.id.viewCart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Sepetim Görüntüle vİEW CART", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
