package com.artopidea.elisioninfotech.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.artopidea.elisioninfotech.Activity.BuyProActivity;
import com.artopidea.elisioninfotech.R;

public class Fragment1 extends Fragment {

    ImageView pro_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        idBinding(view);

        pro_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BuyProActivity.class));
            }
        });

        return view;
    }

    public void idBinding(View view) {
        pro_image = view.findViewById(R.id.pro_image);
    }
}