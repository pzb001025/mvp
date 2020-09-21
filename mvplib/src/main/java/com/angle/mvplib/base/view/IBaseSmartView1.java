package com.angle.mvplib.base.view;

import com.angle.mvplib.base.presenter.IBasePresenter;
import com.angle.mvplib.base.presenter.IBaseSmartPresenter1;
import com.angle.mvplib.data.net.resposne.MvpResponse;

public interface IBaseSmartView1<D ,P extends IBaseSmartPresenter1> extends IBaseView<P> {
//    做请求结果
    void onResult(MvpResponse<D> data);
}
