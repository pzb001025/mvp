package com.angle.mvplib.base.presenter;

import com.angle.mvplib.base.view.IBaseView;

public interface IBasePresenter<V extends IBaseView> {
    //    P层方法抽取
    void onBindView(V view);//绑定View

    void unBindView();//解绑View

    boolean cancelRequest();//是否支持取消
}
