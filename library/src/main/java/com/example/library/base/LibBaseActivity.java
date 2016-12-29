package com.example.library.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.permissions.PermissionUtils;
import com.example.library.tools.NetWorkUtils;
import com.example.library.tools.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import rx.Subscription;

/**
 * Created by Administrator on 2016/12/9.
 * 实现网络变化监听，通过RxBus发送广播
 */

public class LibBaseActivity extends AppCompatActivity {


    private NetWorkReceiver receiver;
    private Subscription rxSubscription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNetworkReceiver();
        initPermissions();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
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
        if (rxSubscription != null && !rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.getInstance().setRequestPermissions(requestCode, permissions, grantResults);
    }

    public void initPermissions() {
    }
}
