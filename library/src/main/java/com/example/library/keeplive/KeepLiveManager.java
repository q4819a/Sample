package com.example.library.keeplive;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

/**
 * Created by Administrator on 2016/12/19.
 */

public class KeepLiveManager {

    private static KeepLiveManager instance;
    private KeepLiveReceiver receiver;

    private KeepLiveManager() {
    }

    public static KeepLiveManager getInstance() {
        if (instance == null) {
            instance = new KeepLiveManager();
        }
        return instance;
    }


    public void startKeepLiveActivity(Context context) {
        KeepLiveActivity.start(context);
    }

    public void finishKeepLiveActivity() {
        if (KeepLiveActivity.instance != null)
            KeepLiveActivity.instance.finish();
    }


    public void registerKeepLiveReceiver(Context context) {
        receiver = new KeepLiveReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        context.registerReceiver(receiver, filter);
    }

    public void startLiveService(Context context) {
        KeepLiveService.start(context);
    }

    public void setForeground(Service keepLiveService, Service innerService) {
        final int foregroundPushId = 1;
        if (keepLiveService != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                keepLiveService.startForeground(foregroundPushId, new Notification());
            } else {
                keepLiveService.startForeground(foregroundPushId, new Notification());
                if (innerService != null) {
                    innerService.startForeground(foregroundPushId, new Notification());
                    innerService.stopSelf();
                }
            }
        }

    }

}
