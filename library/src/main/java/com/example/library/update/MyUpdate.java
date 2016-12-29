package com.example.library.update;

import android.content.Context;

import com.example.library.tools.ToastUtils;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyUpdate implements UpdateType {
    private boolean comple = false;//是否强制更新
    private String url;
    private Context context;

    public MyUpdate(boolean comple) {
        this.comple = comple;
    }

    public MyUpdate(Context context, boolean comple, String url) {
        this.context = context;
        this.url = url;
        this.comple = comple;
    }

    @Override
    public void checkUpgrade() {
        ToastUtils.showShortToast("自己服务器检查更新");
    }

    public void showUpdateMsg() {

    }

}
