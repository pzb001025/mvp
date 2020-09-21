package com.angle.mvplib.data.net.request;

public class PostRequset<T> extends MvpRequest<T> {
    public PostRequset(String url) {
        super(url);
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.POST;
    }
}
