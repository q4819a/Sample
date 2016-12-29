package com.example.library.update;

import android.content.Context;

/**
 * Created by Administrator on 2016/12/27.
 */

public class UpdateUtils {

    private static UpdateUtils instance;
    private UpdateType updateType;

    private UpdateUtils() {
    }

    public static final UpdateUtils getInstance() {
        if (instance == null) {
            instance = new UpdateUtils();
        }
        return instance;
    }

    /**
     * 如果是三方更新直接调用
     */
    public void checkUpdate() {
        setUpdateType(new BuylyUpdate());
        update();
    }

    /**
     * 检测更新
     *
     * @param context
     * @param type    更新方式（1.三方服务器更新。2.自己服务器更新）
     * @param comple  是否强制更新（true.是  false.否）强制更新对三方更新不起作用，三方强制更新在三方后台配置
     * @param url     下载地址(只有自己服务器更新的时候使用)
     */
    public void checkUpdate(Context context, int type, boolean comple, String url) {
        if (type == 1) {
            setUpdateType(new BuylyUpdate());
        } else {
            setUpdateType(new MyUpdate(context, comple, url));
        }
        update();
    }


    /**
     * 设置更新方式（三方更新或自己服务器更新）
     */
    private void setUpdateType(UpdateType type) {
        this.updateType = type;
    }


    private void update() {
        updateType.checkUpgrade();
    }


}
