package com.example.mallshop.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mallshop.R;
import com.example.mallshop.home.entity.GoodsDetailData;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

/**
 * Time: 2020/9/16  17:16
 * Author: Lenovo.pzb
 * Project: MallShop
 */
public class RlvPicAdapter extends RecyclerView.Adapter {

    private List<GoodsDetailData.CommentBean.DataBean.PicListBean> pic_list;
    private Context context;

    public RlvPicAdapter(List<GoodsDetailData.CommentBean.DataBean.PicListBean> pic_list, Context context) {
        this.pic_list = pic_list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_count_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        GoodsDetailData.CommentBean.DataBean.PicListBean bean = pic_list.get(position);
        Glide.with(context).load(bean.getPic_url()).into(vh.imageCount);
    }

    @Override
    public int getItemCount() {
        return pic_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageCount;

        public ViewHolder(View rootView) {
            super(rootView);
            this.imageCount = (ImageView) rootView.findViewById(R.id.imageCount);
        }

    }
}
