package com.angle.mvplib.base.presenter;


import com.angle.mvplib.base.model.IBaseCallBack;
import com.angle.mvplib.base.view.IBaseSmartView2;
import com.angle.mvplib.data.net.request.MvpRequest;
import com.angle.mvplib.data.net.resposne.MvpResponse;

import io.reactivex.rxjava3.disposables.Disposable;

public class BaseSmartPresenter2<D1, D2, V extends IBaseSmartView2<D1, D2, ?>> extends BaseSmartPresenter1<D1, V> implements IBaseSmartPresenter2<D1, D2, V> {

    protected Class<D2> mType2;

    @Override
    public void setType2(Class<D2> type) {
        mType2 = type;
    }

    @Override
    public void doRequest2(MvpRequest<D2> request) {
        mModel.doRequest(getLifecycleProvider(), request, new IBaseCallBack<D2>() {
            @Override
            public void onHandDisposable(Disposable disposable) {
               handDisposable(disposable);
            }

            @Override
            public void onResult(MvpResponse<D2> response) {
                if (mView != null) {
                    mView.onResult2(response);
                }
            }
        });
    }

}
