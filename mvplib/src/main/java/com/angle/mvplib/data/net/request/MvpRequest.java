package com.angle.mvplib.data.net.request;

import com.angle.mvplib.manager.MvpManager;

import java.util.HashMap;

public class MvpRequest<T> {

    protected String url;
    protected RequestType requestType = RequestType.FIRST;  // 第一次请求 0，刷新1 加载更多2
    protected RequestMethod requestMethod; // 1 post,2 get
    protected HashMap<String, Object> mParams; // 请求参数
    protected HashMap<String, Object> mHeaders; // 请求头
    private Class<T> type;

    protected boolean isEnableCancel;  // 网络请求是否支持取消

    public MvpRequest() {
        if (MvpManager.getConfig().getParamsGetter() != null) {
            mParams = MvpManager.getConfig().getParamsGetter().getParams();
            mHeaders = MvpManager.getConfig().getParamsGetter().getHeaders();
        }
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public MvpRequest(String url) {
//        this();
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public HashMap<String, Object> getParams() {
        return mParams == null ? new HashMap<>() : mParams;
    }

    public void setParams(HashMap<String, Object> params) {
        if (mParams != null) {
            mParams.putAll(params);
        } else {
            mParams = params;
        }
    }

    public MvpRequest<T> putParams(String key, Object value) {
        if (mParams == null) {
            mParams = new HashMap<>();
        }

        mParams.put(key, value);
        return this;
    }

    public HashMap<String, Object> getHeaders() {
        return mHeaders == null ? new HashMap<>() : mHeaders;
    }

    public void setHeaders(HashMap<String, Object> headers) {
        this.mHeaders = headers;
    }

    public boolean isEnableCancel() {
        return isEnableCancel;
    }

    public void setEnableCancel(boolean enableCancel) {
        isEnableCancel = enableCancel;
    }
}
