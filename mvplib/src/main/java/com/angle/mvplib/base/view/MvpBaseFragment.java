package com.angle.mvplib.base.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.angle.mvplib.base.presenter.IBasePresenter;

public abstract class MvpBaseFragment<P extends IBasePresenter> extends BaseFragment implements IBaseView<P> {
//    得到P层对象
    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        创建P层方法
        mPresenter = createPresenter();
//        绑定View
        mPresenter.onBindView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        解绑View
        mPresenter.unBindView();
    }
}
