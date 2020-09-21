package com.angle.mvplib.base.view;

import com.angle.mvplib.base.presenter.IBasePresenter;
// View层方法抽取
public interface IBaseView<P extends IBasePresenter> {
    P createPresenter();
}
