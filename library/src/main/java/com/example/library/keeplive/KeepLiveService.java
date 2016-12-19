package com.example.library.keeplive;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/12/19.
 */

public class KeepLiveService extends Service {

    private KeepLiveService liveService;

    public static void start(Context context) {
        Intent starter = new Intent(context, KeepLiveService.class);
        context.startService(starter);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        liveService = this;
        Logger.i("启动保活服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public class InnerService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            KeepLiveManager.getInstance().setForeground(liveService, this);
            return super.onStartCommand(intent, flags, startId);
        }
    }

}
