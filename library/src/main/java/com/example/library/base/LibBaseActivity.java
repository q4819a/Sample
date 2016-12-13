package com.example.library.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.tools.NetWorkUtils;
import com.example.library.tools.ToastUtils;
import com.pgyersdk.activity.FeedbackActivity;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/12/9.
 * 实现网络变化监听，通过RxBus发送广播
 */

public class LibBaseActivity extends AppCompatActivity {


    private NetWorkReceiver receiver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNetworkReceiver();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        initPgyFeedback();


    }

    /**
     * 摇一摇反馈
     */
    private void initPgyFeedback() {
        // 自定义摇一摇的灵敏度，默认为950，数值越小灵敏度越高。
        PgyFeedbackShakeManager.setShakingThreshold(800);
        // 以对话框的形式弹出
        // PgyFeedbackShakeManager.register(LibBaseActivity.this);
        // 以Activity的形式打开，这种情况下必须在AndroidManifest.xml配置FeedbackActivity
        // 打开沉浸式,默认为false
        FeedbackActivity.setBarImmersive(true);
        PgyFeedbackShakeManager.register(LibBaseActivity.this, false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unNetworkReceiver();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * 注册网络变化广播
     */
    private void initNetworkReceiver() {
        receiver = new NetWorkReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    /**
     * 取消网络变化广播
     */
    private void unNetworkReceiver() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    class NetWorkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (NetWorkUtils.isConnectedByState(context)) {
                } else {
                    ToastUtils.showShortToast(getString(R.string.network_err));
                }
            }

        }
    }

}
