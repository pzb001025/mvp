package com.angle.mvplib.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.angle.mvplib.util.MvpConfig;
import com.angle.mvplib.util.MvpSpUtils;
import com.angle.mvplib.util.MvpUtils;

import java.io.File;


public class MvpManager {

    private static final String SP_VERSION_CODE = "version_code";

    private static Context mApplication;
    private static MvpConfig mConfig;

    private static UiHandler mUiHandler;

    public static void init(MvpConfig config) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalThreadStateException("Mvp.init(context) 方法 必须调用在主线程进行初始化，不用担心，Mvp 的具体初始化操作不会占用主线程");
        }

        mConfig = config;
        mUiHandler = new UiHandler();
        mApplication = config.getContext();

    }


    public static Context getContext() {
        if (mApplication == null) {
            throw new NullPointerException("Mvp 框架还没有初始化，请初始化后在使用");
        }
        return mApplication;
    }


    static void postUI(Runnable runnable) {
        mUiHandler.post(runnable);
    }


    static class UiHandler extends Handler {
    }


    // 是否显示引导页
    public static boolean isShowGuidePage() {
//        得到安装的版本号
        int versionCode = MvpSpUtils.getInt(SP_VERSION_CODE);
//     获取从SP保存的版本号与安装之后的版本对比如果不一致返回true
        if (versionCode == -1 || versionCode != MvpUtils.getAppVersionCode(mApplication)) {
            MvpSpUtils.saveApply(SP_VERSION_CODE, MvpUtils.getAppVersionCode(mApplication));
            return true;
        }

        return false;
    }


    public static void launchFail() {
        MvpSpUtils.remove(SP_VERSION_CODE);
    }


    public static MvpConfig getConfig() {
        return mConfig;
    }


    // 获取本地文件地址
    public static File getExternalCacheDir() {
        return mApplication.getExternalCacheDir();
    }

}
