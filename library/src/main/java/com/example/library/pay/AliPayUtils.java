package com.example.library.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.example.library.constant.Constant;
import com.example.library.rxbus.RxBus;
import com.example.library.rxbus.RxEvent;
import com.example.library.tools.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/15.
 */

public class AliPayUtils {

    private Activity activity;
    private static AliPayUtils instance;

    private static final int SDK_PAY_FLAG = 1;

    private AliPayUtils(Activity activity) {
        this.activity = activity;
    }

    public static AliPayUtils getInstance(Activity activity) {
        if (instance == null) {
            instance = new AliPayUtils(activity);
        }
        return instance;
    }

    /**
     * 支付接口
     */
    public void pay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Logger.i("msg:" + result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShortToast("支付成功");
                        RxBus.getInstance().post(new RxEvent(Constant.ALIPAY_PAY_SUCCESS));
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShortToast("支付失败");
                        RxBus.getInstance().post(new RxEvent(Constant.ALIPAY_PAY_FAIL));
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

}
