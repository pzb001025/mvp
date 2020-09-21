package com.angle.mvplib.base.view;


import com.angle.mvplib.base.presenter.IBaseSmartPresenter2;
import com.angle.mvplib.data.net.resposne.MvpResponse;

public interface IBaseSmartView2<D1,D2,P extends IBaseSmartPresenter2<D1,D2,?>> extends IBaseSmartView1<D1,P> {
    void onResult2(MvpResponse<D2> response);
}
