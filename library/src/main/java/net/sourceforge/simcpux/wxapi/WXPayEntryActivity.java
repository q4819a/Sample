package net.sourceforge.simcpux.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.library.constant.Constant;
import com.example.library.rxbus.RxBus;
import com.example.library.rxbus.RxEvent;
import com.example.library.tools.ToastUtils;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case 0:
                ToastUtils.showShortToast("支付成功");
                RxBus.getInstance().post(new RxEvent(Constant.WXPAY_PAY_SUCCESS));
                break;
            case -1:
                ToastUtils.showShortToast("支付失败");
                RxBus.getInstance().post(new RxEvent(Constant.WXPAY_PAY_FAIL));
                break;
            case -2:
                ToastUtils.showShortToast("取消支付");
                RxBus.getInstance().post(new RxEvent(Constant.WXPAY_PAY_CANCEL));
                break;
            default:
                break;
        }
    }
}