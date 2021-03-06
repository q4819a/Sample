package com.example.library.base;

import android.app.Application;
import android.content.Context;

import com.example.library.tools.ToastUtils;
import com.lzy.okgo.OkGo;

/**
 * Created by Administrator on 2016/12/9.
 */

public abstract class LibBaseApp extends Application {

    public static Context app;
    public static String city;
    public static String address;
    public static double latitude;
    public static double longitude;

    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
        initLogger();
        initToast();
        initUM();
        initOKGO();
        initBuyly();
    }

    /**
     * 初始化Buyly
     * 参考文档：https://bugly.qq.com/docs/user-guide/instruction-manual-android-upgrade/?v=20161219153020#sdk_1
     */
    protected abstract void initBuyly();

    /**
     * 初始化OKGO
     * 地址：https://github.com/jeasonlzy/okhttp-OkGo
     */
    protected void initOKGO() {
        OkGo.init(this);
    }


    /**
     * 初始化logger
     * 地址：https://github.com/orhanobut/logger
     */
    public abstract void initLogger();


    /**
     * 初始化Toast
     */
    private void initToast() {
        ToastUtils.init(this);
    }


    /**
     * 初始化友盟统计，并设置appkey
     * 地址：http://dev.umeng.com/analytics/android-doc/integration
     * MobclickAgent.setDebugMode( true );设置是否开启调试模式
     * 方法中应该添加：
     * MobclickAgent. startWithConfigure(UMAnalyticsConfig config)
     * UMAnalyticsConfig(Context context, String appkey, String channelId)
     */
    public abstract void initUM();


}
