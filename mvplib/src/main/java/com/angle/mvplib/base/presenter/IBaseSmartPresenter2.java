package com.angle.mvplib.base.presenter;


import com.angle.mvplib.base.view.IBaseSmartView2;
import com.angle.mvplib.data.net.request.MvpRequest;

public interface IBaseSmartPresenter2<D1,D2,V extends IBaseSmartView2<D1,D2,?>> extends IBaseSmartPresenter1<D1,V> {
    void setType2(Class<D2> type);
    void doRequest2(MvpRequest<D2> request);

}
