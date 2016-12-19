package com.example.library.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

/**
 * Created by Administrator on 2016/12/19.
 */
public class PermissionUtils {

    private static PermissionUtils instance;

    private PermissionUtils() {
    }

    public static PermissionUtils getInstance() {
        if (instance == null) {
            instance = new PermissionUtils();
        }
        return instance;
    }

    private OnRegisterPermissionCall call;
    private int code;
    private String message;

    /**
     * 注册权限
     *
     * @param context
     * @param code       注册code，回调时候使用
     * @param permission 权限数组，不允许同时注册多个权限
     */
    public void registerPermission(Context context, int code, String permission) {
        this.code = code;
        checkPermission(context, permission, code);
    }

    /**
     * 设置权限回调
     */
    public void setRegisterPermissionCall(OnRegisterPermissionCall call) {
        this.call = call;
    }

    /**
     * 如果没有权限时候弹窗文字（非必选）
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 回调成功与失败
     */
    public void setRequestPermissions(int requestCode, @NonNull String[] permissions,
                                      @NonNull int[] grantResults) {
        if (requestCode == code && grantResults.length > 0 && grantResults[0] == PackageManager
                .PERMISSION_GRANTED) {
            //授权成功
            call.registerSuccess(code);
        } else {
            call.registerFail(code);
        }
    }


    /**
     * 检查是否有该权限，如果有不再注册
     */
    private void checkPermission(final Context context, final String permission, final int code) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager
                .PERMISSION_GRANTED) {
            //没有该权限,去注册
            if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    permission)) {
                showMessageOKCancel(context, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri packageURI = Uri.parse("package:" + context.getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                packageURI);
                        context.startActivity(intent);
                    }
                });
            }
            register(context, new String[]{permission}, code);
        } else {
            //有该权限
            call.registerSuccess(code);
        }
    }


    private void showMessageOKCancel(Context context, DialogInterface
            .OnClickListener okListener) {
        if (TextUtils.isEmpty(message)) {
            message = "使用该功能前需要您先授予相应的权限，是否跳转授权界面？";
        }
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage(message)
                .setPositiveButton("去授权", okListener)
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    /**
     * 注册权限
     */
    private void register(Context context, String[] s, int code) {
        ActivityCompat.requestPermissions((Activity) context, s, code);
    }


    public interface OnRegisterPermissionCall {

        void registerSuccess(int code);

        void registerFail(int code);
    }


}