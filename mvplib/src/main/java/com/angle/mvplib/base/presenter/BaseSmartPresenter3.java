package com.angle.mvplib.base.presenter;


import com.angle.mvplib.base.model.IBaseCallBack;
import com.angle.mvplib.base.view.IBaseSmartView3;
import com.angle.mvplib.data.net.request.MvpRequest;
import com.angle.mvplib.data.net.resposne.MvpResponse;

import io.reactivex.rxjava3.disposables.Disposable;

public class BaseSmartPresenter3<D1, D2, D3, V extends IBaseSmartView3<D1, D2, D3, ?>> extends BaseSmartPresenter2<D1, D2, V> implements IBaseSmartPresenter3<D1, D2, D3, V> {

    protected Class<D3> mType3;

    @Override
    public void setType3(Class<D3> type) {
        mType3 = type;
    }

    @Override
    public void doRequest3(MvpRequest<D3> request) {
        mModel.doRequest(getLifecycleProvider(), request, new IBaseCallBack<D3>() {
            @Override
            public void onHandDisposable(Disposable disposable) {
                handDisposable(disposable);
            }

            @Override
            public void onResult(MvpResponse<D3> response) {
                if (mView != null) {
                    mView.onResult3(response);
                }
            }
        });
    }
}
