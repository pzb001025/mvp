package com.angle.mvplib.data.net.resposne;

import com.angle.mvplib.data.net.request.RequestType;

public class MvpResponse<D> {
    private String msg;
    private int code;
    private D data;
    private RequestType requestType = RequestType.FIRST; // 是第一次请求回来？刷新回来？加载更多回来？
    private ResponseType type = ResponseType.SERVER;// 数据从哪儿回来的？ 服务器，sdcard, 内存

    public ResponseType getType() {
        return type;
    }

    public MvpResponse<D> setType(ResponseType type) {
        this.type = type;
        return this;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public MvpResponse<D> setRequestType(RequestType requestType) {
        this.requestType = requestType;
        return this;
    }

    public boolean isOk() {
        return data != null;
    }

    public String getMsg() {
        return msg;
    }

    public MvpResponse<D> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public MvpResponse<D> setCode(int code) {
        this.code = code;

        return this;
    }

    public D getData() {
        return data;
    }

    public MvpResponse<D> setData(D data) {
        this.data = data;
        return this;
    }
}
