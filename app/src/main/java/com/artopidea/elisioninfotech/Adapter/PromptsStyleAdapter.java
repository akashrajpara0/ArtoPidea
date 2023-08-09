package com.artopidea.elisioninfotech.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.artopidea.elisioninfotech.Model.PromptsStyleData;
import com.artopidea.elisioninfotech.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class PromptsStyleAdapter extends RecyclerView.Adapter<PromptsStyleAdapter.ViewHolder> {

    private List<PromptsStyleData> promptsStyleData;
    private Activity context;

    @Override
    public int getItemViewType(int i) {
        return i;
    }

    public PromptsStyleAdapter(Activity activity, List<PromptsStyleData> list) {
        this.context = activity;
        this.promptsStyleData = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_prompts_style, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.style_name_text.setText(promptsStyleData.get(i).getName());

        try {
            Glide.with(context).load("https://api.artopidea.com/storage/" +
                    promptsStyleData.get(i).getStyle_img()).into(viewHolder.style_image);
        } catch (Exception unused) {
        }

    }

    @Override
    public int getItemCount() {
        return this.promptsStyleData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView style_image;
        TextView style_name_text;

        ViewHolder(View view) {
            super(view);

            style_image = (ImageView) view.findViewById(R.id.style_image);
            style_name_text = (TextView) view.findViewById(R.id.style_name_text);

        }
    }
}