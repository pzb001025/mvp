package com.angle.mvplib.data.entity;

//  封装一个Bean类
public class HttpReult<D> {
    /*  {'code': '状态码', 'message': '相关提示', 'data': '数据包'}*/

    private int errno;
    private String errmsg;
    private D data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpReult{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }
}
