package com.example.mallshop.shop.activity;

import android.view.View;

import com.angle.mvplib.base.view.BaseSmartFragment1;
import com.angle.mvplib.common.Constarct;
import com.angle.mvplib.data.net.request.GetRequset;
import com.angle.mvplib.data.net.resposne.MvpResponse;
import com.example.mallshop.R;

public class AdressFragment extends BaseSmartFragment1 {

    @Override
    protected void initVeiw() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_adress;
    }

    @Override
    protected void bindingView(View view) {
        super.bindingView(view);
        GetRequset<Object> requset = new GetRequset<>(Constarct.URL.REGIONLIST);
    }

    @Override
    public void onResult(MvpResponse data) {

    }
}