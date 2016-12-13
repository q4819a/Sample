package com.example.library.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/10.
 */
public class ToastUtils {

    private static Toast mToast;

    public static void init(Context context) {
        mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }


    public static void showShortToast(String text) {
        mToast.setText(text);
        mToast.show();
    }


}
