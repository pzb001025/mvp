package com.angle.mvplib.data.net.request;

public class GetRequset<T> extends MvpRequest<T> {
    public GetRequset(String url) {
        super(url);
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }
}
