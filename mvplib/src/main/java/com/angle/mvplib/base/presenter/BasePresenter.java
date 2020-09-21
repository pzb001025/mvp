package com.angle.mvplib.base.presenter;

import com.angle.mvplib.base.view.IBaseView;
import com.trello.rxlifecycle4.LifecycleProvider;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
//    得到View对象
    protected V mView;
//  实现方法
    @Override
    public void onBindView(V view) {
        mView = view;
    }

    @Override
    public void unBindView() {
        mView = null;
    }
//  得到LifecycleProvider对象
    public LifecycleProvider getLifecycleProvider() {
        return (LifecycleProvider) mView;
    }
}
