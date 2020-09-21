package com.angle.mvplib.base.presenter;

import com.angle.mvplib.base.model.BaseRepository;
import com.angle.mvplib.base.model.IBaseCallBack;
import com.angle.mvplib.base.view.IBaseSmartView1;
import com.angle.mvplib.data.net.request.MvpRequest;
import com.angle.mvplib.data.net.resposne.MvpResponse;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class BaseSmartPresenter1<D, V extends IBaseSmartView1<D, ?>> extends BasePresenter<V> implements IBaseSmartPresenter1<D, V> {
    protected BaseRepository mModel;
    protected CompositeDisposable compositeDisposable;
    protected Class<D> mType;
//  实现setType方法
    @Override
    public void setType(Class<D> type) {
        mType = type;
    }
//   得到具体的model
    public BaseSmartPresenter1() {
        mModel = new BaseRepository();
    }

    public BaseSmartPresenter1(BaseRepository mModel) {
        this.mModel = mModel;
    }

    //  做请求方法
    @Override
    public void doRequest(MvpRequest<D> request) {
//        设置Type数据
        request.setType(mType);
//        做请求将model数据传View
        mModel.doRequest(getLifecycleProvider(), request, new IBaseCallBack<D>() {
            @Override
            public void onResult(MvpResponse<D> response) {
                if (mView != null) {
                    mView.onResult(response);
                }
            }
        });
    }

    @Override
    public boolean cancelRequest() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            return true;
        }
        return false;
    }

    public void handDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
