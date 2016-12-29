package com.july.sample.app;

import com.example.library.base.LibBaseApp;
import com.july.sample.R;
import com.july.sample.constant.ExternalConstant;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.umeng.analytics.MobclickAgent;

import java.util.logging.Level;

/**
 * Created by Administrator on 2016/12/10.
 */
public class App extends LibBaseApp {

    public boolean isDebug = true;//是否是调试模式

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void initBuyly() {
        Beta.autoInit = true;
        Beta.autoCheckUpgrade = false;//不自动检测更新
        Beta.largeIconId = R.mipmap.ic_launcher;
        Beta.smallIconId = R.mipmap.ic_launcher;
        Beta.showInterruptedStrategy = true;
       /* 参数1：上下文对象
        参数2：注册时申请的APPID
        参数3：是否开启debug模式，true表示打开debug模式，false表示关闭调试模式*/
        Bugly.init(this, ExternalConstant.BUGLY_APP_ID, isDebug);
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
        OkGo.getInstance().debug("OkGo", Level.INFO, isDebug);
    }
}
