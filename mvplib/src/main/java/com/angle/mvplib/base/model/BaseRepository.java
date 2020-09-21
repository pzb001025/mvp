package com.angle.mvplib.base.model;

import android.util.Log;

import com.angle.mvplib.data.entity.HttpReult;
import com.angle.mvplib.data.net.resposne.MvpResponse;
import com.angle.mvplib.data.net.ok.DataService;
import com.angle.mvplib.data.net.request.MvpRequest;
import com.angle.mvplib.util.ParameterizedTypeImpl;
import com.google.gson.Gson;
import com.trello.rxlifecycle4.LifecycleProvider;
import com.trello.rxlifecycle4.RxLifecycle;
import com.trello.rxlifecycle4.android.ActivityEvent;
import com.trello.rxlifecycle4.android.FragmentEvent;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BaseRepository implements IBaseRepository {
    //    创建ladam表达式得到空对象
    Consumer consumer = o -> {
    };

    //创建请求方法
    @Override
    public <T> void doRequest(LifecycleProvider lifecycleProvider, MvpRequest<T> request, IBaseCallBack<T> callBack) {
        doRequest(lifecycleProvider, request, callBack, consumer);
    }

    // 做请求GETorPOST

    /**
     * @param lifecycleProvider //监听生命周期
     * @param request           //请求bean
     * @param callBack          //接口回掉
     * @param doBackground      //做缓存
     * @param <T>
     */
    public <T> void doRequest(LifecycleProvider lifecycleProvider, MvpRequest<T> request, IBaseCallBack<T> callBack, Consumer<MvpResponse<T>> doBackground) {
        switch (request.getRequestMethod()) {
            case GET:
                doObservable(lifecycleProvider, request, callBack, doBackground, DataService.getApiService().doGet(request.getUrl(), request.getParams()));
                break;
            case POST:
                doObservable(lifecycleProvider, request, callBack, doBackground, DataService.getApiService().doPost(request.getUrl(), request.getParams()));
                break;
        }
    }

    //   做数据请求

    /**
     * @param lifecycleProvider 将听activity/生fragment生命周期切断model层
     * @param request           请求
     * @param callBack          回传数据
     * @param doBackground      最数据缓存
     * @param observable        做数据园
     * @param <T>
     */
    private <T> void doObservable(LifecycleProvider lifecycleProvider, MvpRequest<T> request, IBaseCallBack<T> callBack, Consumer<MvpResponse<T>> doBackground, Observable<String> observable) {
//      如果使用者没有设置Type则利用反射得到该类型
        if (request.getType() == null && callBack != null) {
            Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();//获得该类的接口或子类
            ParameterizedType genericInterface = (ParameterizedType) genericInterfaces[0];//得到该范型参数
            Type[] actualTypeArguments = genericInterface.getActualTypeArguments();//得到范型化参数数组
            Class<T> type = (Class<T>) actualTypeArguments[0];//获得该范型化参数
            request.setType(type);//设置type

        }
//        得到observale对象做处理
        @NonNull Observable<MvpResponse<T>> observable1 = observable
                .map(jsonData(request))//解析数据
                .doOnNext(doBackground)//做缓存
                .subscribeOn(Schedulers.io())//使用子线程处理该操作
                .observeOn(AndroidSchedulers.mainThread());//切换ui线程
        /*
         * 如果lifecycleProvider不为空
         * 判断是否Activity*/
        if (lifecycleProvider != null) {
            if (lifecycleProvider instanceof RxAppCompatActivity) {
//                在anctivty Destory（）切断
                observable1 = observable1.compose(RxLifecycle.bindUntilEvent(lifecycleProvider.lifecycle(), ActivityEvent.DESTROY));
            } else {
//                在 Fragment Destory（）切断
                observable1 = observable1.compose(RxLifecycle.bindUntilEvent(lifecycleProvider.lifecycle(), FragmentEvent.DESTROY));
            }
        }
//        得到结果回传
        observable1.subscribe(new Observer<MvpResponse<T>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                是否切断
                if (request.isEnableCancel() && callBack != null) {
                    callBack.onHandDisposable(d);
                }
            }

            @Override
            public void onNext(@NonNull MvpResponse<T> tMvpResponse) {
                callBack.onResult(tMvpResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
//                callBack.onResult(new MvpResponse<T>().setMsg(e.getMessage()));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    //   转换字符串
    private <T> Function<String, MvpResponse<T>> jsonData(MvpRequest<T> request) {
        return new Function<String, MvpResponse<T>>() {
            @Override
            public MvpResponse<T> apply(String s) throws Throwable {
                ParameterizedTypeImpl parameterizedType = new ParameterizedTypeImpl(HttpReult.class, new Type[]{request.getType()});
                HttpReult<T> data = new Gson().fromJson(s, parameterizedType);
                Log.d(TAG, "apply: ");
                if (data.getErrno() == 0) {
                    if (data.getData() != null) {
                        return new MvpResponse<T>().setData(data.getData()).setCode(data.getErrno());
                    } else {
                        return new MvpResponse<T>().setCode(data.getErrno()).setMsg("服务器异常");
                    }
                } else {
                    return new MvpResponse<T>().setCode(data.getErrno()).setMsg(data.getErrmsg());
                }
            }
        };
    }

    private static final String TAG = "BaseRepository";
}
