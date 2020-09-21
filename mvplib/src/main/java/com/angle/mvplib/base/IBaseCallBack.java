package com.angle.mvplib.base;


import com.angle.mvplib.data.net.resposne.MvpResponse;

import io.reactivex.rxjava3.disposables.Disposable;

public interface IBaseCallBack<T>{

    void onResult(MvpResponse<T> response);

    default void onStart(Disposable disposable){
    }

}
