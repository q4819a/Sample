package com.july.sample.app;

import com.example.library.base.LibBaseApp;
import com.july.sample.constant.ExternalConstant;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;

import java.util.logging.Level;

/**
 * Created by Administrator on 2016/12/10.
 */
public class App extends LibBaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void initLogger() {
        Logger.init("JULY");             // default PRETTYLOGGER or use just init()
    }

    @Override
    public void initUM() {
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, ExternalConstant.UM_APPKEY, ExternalConstant.UM_CHANNELID));
    }

    @Override
    protected void initOKGO() {
        super.initOKGO();
        OkGo.getInstance().debug("OkGo", Level.INFO, true);
    }
}
