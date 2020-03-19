package com.example.cafestalk.ui.fooddetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cafestalk.Model.OrderDetail;
import com.example.cafestalk.R;

import java.util.ArrayList;

public class FoodDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_food_detail, container, false);
        TextView t = root.findViewById(R.id.textview_fooddetailname);
        ImageView i = root.findViewById(R.id.image_fooddetail);
        ArrayList<String> values = getArguments().getStringArrayList("mFoodDetail");
        values.get(0);
        OrderDetail orderDetail = new OrderDetail();
        t.setText(orderDetail.getName());
        i.setImageResource(orderDetail.getImage());

/*        Bundle bundle = this.getArguments();
        bundle.getParcelableArray("mFoodDetail");
        if (bundle != null) {
            bundle.get("mFoodDetail");

        }*/
        return root;

    }
}
