package com.example.mallshop.home.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mallshop.R;
import com.example.mallshop.home.entity.IndexData;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<IndexData.TopicListBean> data;

    public TopicAdapter(Context context, List<IndexData.TopicListBean> data) {

        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_home_topic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        IndexData.TopicListBean bean = data.get(position);
        Glide.with(context).load(bean.getItem_pic_url()).into(vh.img_topic);
        vh.txt_topic_name.setText(bean.getTitle());
        vh.txt_topic_price.setText(bean.getPrice_info() + "");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_topic;
        public TextView txt_topic_name;
        public TextView txt_topic_price;

        public ViewHolder(View rootView) {
            super(rootView);
            this.img_topic = (ImageView) rootView.findViewById(R.id.img_topic);
            this.txt_topic_name = (TextView) rootView.findViewById(R.id.txt_topic_name);
            this.txt_topic_price = (TextView) rootView.findViewById(R.id.txt_topic_price);
        }

    }
}