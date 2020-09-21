package com.angle.mvplib.base.view;


import com.angle.mvplib.base.presenter.IBaseSmartPresenter3;
import com.angle.mvplib.data.net.resposne.MvpResponse;

public interface IBaseSmartView3<D1,D2,D3,P extends IBaseSmartPresenter3<D1,D2,D3,?>> extends IBaseSmartView2<D1,D2,P> {
    void onResult3(MvpResponse<D3> response);
}
