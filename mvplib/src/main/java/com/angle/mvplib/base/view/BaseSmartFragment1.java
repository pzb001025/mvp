package com.angle.mvplib.base.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.angle.mvplib.base.presenter.BaseSmartPresenter1;
import com.angle.mvplib.data.net.request.MvpRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseSmartFragment1<D> extends MvpBaseFragment<BaseSmartPresenter1<D, ?>> implements IBaseSmartView1<D, BaseSmartPresenter1<D, ?>> {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType superclass = (ParameterizedType) type;
            Class<D> actualTypeArgument = (Class<D>) superclass.getActualTypeArguments()[0];
            mPresenter.setType(actualTypeArgument);
        }
    }

    @Override
    public BaseSmartPresenter1<D, ?> createPresenter() {
        return new BaseSmartPresenter1<>();
    }

    public void doRequest(MvpRequest<D> request) {
        mPresenter.doRequest(request);
    }
}
