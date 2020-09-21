package com.angle.mvplib.base.presenter;


import com.angle.mvplib.base.view.IBaseSmartView3;
import com.angle.mvplib.data.net.request.MvpRequest;

public interface IBaseSmartPresenter3<D1,D2,D3,V extends IBaseSmartView3<D1,D2,D3,?>> extends IBaseSmartPresenter2<D1,D2,V> {
    void setType3(Class<D3> type);
    void doRequest3(MvpRequest<D3> request);

}
