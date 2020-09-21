package com.angle.mvplib.base.presenter;

import com.angle.mvplib.base.view.IBaseSmartView1;
import com.angle.mvplib.data.net.request.MvpRequest;

public interface IBaseSmartPresenter1<D, V extends IBaseSmartView1<D, ?>> extends IBasePresenter<V> {
//   做数据请求
    void doRequest(MvpRequest<D> request);
//  设置请求类型
    void setType(Class<D> type);
}
