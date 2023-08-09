package com.artopidea.elisioninfotech.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.artopidea.elisioninfotech.Activity.BuyProActivity;
import com.artopidea.elisioninfotech.Activity.HighResolutionActivity;
import com.artopidea.elisioninfotech.Activity.ImageRemixActivity;
import com.artopidea.elisioninfotech.Activity.InpaintingActivity;
import com.artopidea.elisioninfotech.Activity.PortraitDepthActivity;
import com.artopidea.elisioninfotech.Activity.PortraitSurfaceActivity;
import com.artopidea.elisioninfotech.Activity.RemoveBgActivity;
import com.artopidea.elisioninfotech.Activity.RemoveTextActivity;
import com.artopidea.elisioninfotech.Activity.ReplaceBackgroundActivity;
import com.artopidea.elisioninfotech.Activity.TextToImageActivity;
import com.artopidea.elisioninfotech.R;

public class Fragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        idBinding(view);

        view.findViewById(R.id.dashboard_1_text_to_image_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        TextToImageActivity.class));
            }
        });

        view.findViewById(R.id.dashboard_2_remove_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        RemoveBgActivity.class));
            }
        });

        view.findViewById(R.id.dashboard_3_high_resolution).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        HighResolutionActivity.class));
            }
        });

        view.findViewById(R.id.dashboard_4_portrait_depth_normals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        PortraitDepthActivity.class));
            }
        });

        view.findViewById(R.id.dashboard_5_replace_background).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        ReplaceBackgroundActivity.class));
            }
        });

        view.findViewById(R.id.dashboard_6_inpainting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        InpaintingActivity.class));
            }
        });

        view.findViewById(R.id.dashboard_7_remove_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        RemoveTextActivity.class));
            }
        });

        view.findViewById(R.id.dashboard_8_portrait_surface_normals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        PortraitSurfaceActivity.class));
            }
        });

        view.findViewById(R.id.dashboard_9_image_remix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        ImageRemixActivity.class));
            }
        });

        view.findViewById(R.id.pro_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        BuyProActivity.class));
            }
        });

        return view;
    }

    public void idBinding(View view) {

    }
}