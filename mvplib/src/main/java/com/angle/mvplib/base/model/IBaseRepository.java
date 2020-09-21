package com.angle.mvplib.base.model;

import com.angle.mvplib.data.net.request.MvpRequest;
import com.trello.rxlifecycle4.LifecycleProvider;

public interface IBaseRepository {
    //    创建请求方法
    <T> void doRequest(LifecycleProvider lifecycleProvider, MvpRequest<T> request, IBaseCallBack<T> callBack);
}
