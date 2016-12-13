package com.example.library.widget.progressdialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.library.R;


/**
 * Created by Administrator on 2016/12/5.
 */
public class ProgressDlg extends ProgressDialog {

    private static ProgressDlg dialog;


    public ProgressDlg(Context context) {
        super(context);
    }

    public ProgressDlg(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 默认样式
     */
    public static void showDialog(Context context, String message) {
        if (dialog == null) {
            dialog = new ProgressDlg(context);
        }
        dialog.setMessage(message);
        dialog.show();
    }

    /**
     * 居中显示
     *
     * @param state 点击屏幕外是否可以消失
     */
    public static void showDialog(Context context, String message, boolean state) {
        if (dialog == null) {
            dialog = new ProgressDlg(context, R.style.progressDlg);
        }
        dialog.setCanceledOnTouchOutside(state);
        dialog.setMessage(message);
        dialog.show();
    }

    public static void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        dialog = null;
    }
}

