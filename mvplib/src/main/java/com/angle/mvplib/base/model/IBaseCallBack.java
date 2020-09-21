package com.angle.mvplib.base.model;

import com.angle.mvplib.data.net.resposne.MvpResponse;

import io.reactivex.rxjava3.disposables.Disposable;

public interface IBaseCallBack<T> {
//    利用CallBack传递成功结果
    void onResult(MvpResponse<T> response);
//    切断model层方法
    default void onHandDisposable(Disposable disposable) {
    }
}
