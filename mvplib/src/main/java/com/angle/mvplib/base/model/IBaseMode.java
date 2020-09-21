package com.angle.mvplib.base.model;


import com.angle.mvplib.data.net.request.MvpRequest;

public interface IBaseMode {

    <T> void doRequest(MvpRequest<T> request, IBaseCallBack<T> callBack);
}
