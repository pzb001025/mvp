package com.example.mallshop.shop.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.angle.mvplib.base.view.BaseSmartFragment2;
import com.angle.mvplib.common.Constarct;
import com.angle.mvplib.data.net.request.GetRequset;
import com.angle.mvplib.data.net.resposne.MvpResponse;
import com.angle.mvplib.util.ParamsUtils;
import com.example.mallshop.R;
import com.example.mallshop.databinding.FragmentShopBinding;
import com.example.mallshop.shop.adapter.RlvShopAdapter;
import com.example.mallshop.shop.entity.CartData;
import com.example.mallshop.shop.entity.DeleteCartData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopFragment extends BaseSmartFragment2<CartData, DeleteCartData> {

    private FragmentShopBinding root;
    private RlvShopAdapter rlvShopAdapter;

    @Override
    protected void initVeiw() {
        root.rlvShop.setLayoutManager(new LinearLayoutManager(getContext()));
        rlvShopAdapter = new RlvShopAdapter(getContext());
        root.rlvShop.setAdapter(rlvShopAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void bindingView(View view) {
        super.bindingView(view);
        root = FragmentShopBinding.bind(view);

        //获取购物车数据
        GetRequset<CartData> requsetIndex = new GetRequset<>(Constarct.URL.CARTINDEX);
        requsetIndex.setParams(ParamsUtils.getCommonParams());
        requsetIndex.setType(CartData.class);
        doRequest1(requsetIndex);

        //删除购物车数据
        HashMap<String, Object> map = new HashMap<>();
        GetRequset<DeleteCartData> requsetDelete = new GetRequset<>(Constarct.URL.CARTDELETE);
        requsetIndex.setParams(map);
        requsetDelete.setType(DeleteCartData.class);
        doRequest2(requsetDelete);
    }

    @Override
    public void onResult2(MvpResponse<DeleteCartData> response) {
        if (response.getData() != null) {
            Log.d(TAG, "onResult2: " + response.getData().toString());
        }
    }

    @Override
    public void onResult(MvpResponse<CartData> data) {
        if (data.getData() != null) {
            Log.d(TAG, "onResult1: " + data.getData().toString());
        }
    }

    private static final String TAG = "ShopFragment";
}
