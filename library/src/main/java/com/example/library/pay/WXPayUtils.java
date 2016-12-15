package com.example.library.pay;

import android.content.Context;

import com.example.library.tools.ToastUtils;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2016/12/15.
 */

public class WXPayUtils {

    private static WXPayUtils instance;

    private WXPayUtils() {
    }

    public static WXPayUtils getInstance() {
        if (instance == null) {
            instance = new WXPayUtils();
        }
        return instance;
    }


    public void pay(Context context, PayReq req) {
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp("wxd930ea5d5a258f4f");
        if (!msgApi.isWXAppInstalled()) {
            ToastUtils.showShortToast("没有安装微信,请安装后再试");
            return;
        }
        if (!msgApi.isWXAppSupportAPI()) {
            ToastUtils.showShortToast("当前版本不支持支付功能");
            return;
        }
        msgApi.sendReq(req);
    }
}
