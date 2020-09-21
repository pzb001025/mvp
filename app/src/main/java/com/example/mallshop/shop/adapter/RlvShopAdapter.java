package com.example.mallshop.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mallshop.R;
import com.example.mallshop.shop.entity.CartData;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2020/9/18  11:51
 * Author: Lenovo.pzb
 * Project: MallShop
 */
public class RlvShopAdapter extends RecyclerView.Adapter {
    private List<CartData.CartListBean> list = new ArrayList<>();
    private Context context;

    public RlvShopAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<CartData.CartListBean> cartList) {
        cartList.clear();
        if (cartList != null && cartList.size() > 0) {
            list.addAll(cartList);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_shop_item, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
