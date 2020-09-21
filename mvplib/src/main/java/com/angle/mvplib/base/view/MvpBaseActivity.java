package com.angle.mvplib.base.view;

import android.os.Bundle;

import com.angle.mvplib.base.presenter.IBasePresenter;

public abstract class MvpBaseActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView<P> {
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.onBindView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBindView();
    }
}
