package com.angle.mvplib.base.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.angle.mvplib.base.presenter.BaseSmartPresenter1;
import com.angle.mvplib.base.presenter.IBaseSmartPresenter1;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseSmartActivity<D> extends MvpBaseActivity<BaseSmartPresenter1<D, ?>> implements IBaseSmartView1<D, BaseSmartPresenter1<D, ?>> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType){
            ParameterizedType type = (ParameterizedType) superclass;
            Class<D> actualTypeArgument = (Class<D>) type.getActualTypeArguments()[0];
            mPresenter.setType(actualTypeArgument);
        }
    }

    @Override
    public BaseSmartPresenter1<D, ?> createPresenter() {
        return new BaseSmartPresenter1<>();
    }
}
