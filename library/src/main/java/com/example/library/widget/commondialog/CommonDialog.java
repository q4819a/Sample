package com.example.library.widget.commondialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.library.R;

/**
 * Created by Administrator on 2016/12/11.
 */
public class CommonDialog extends Dialog {

    private String title;
    private String msg;
    private String positiveButton;
    private String negativeButton;
    private String nutralButton;

    private OnClickListener click;

    private TextView tv_common_dialog_title;
    private LinearLayout ll_common_dialog_title;
    private TextView tv_common_dialog_msg;
    private LinearLayout ll_common_dialog_negativeButton;
    private LinearLayout ll_common_dialog_nutralButton;
    private LinearLayout ll_common_dialog_positiveButton;
    private Button btn_common_dialog_negativeButton;
    private Button btn_common_dialog_nutralButton;
    private Button btn_common_dialog_positiveButton;


    public CommonDialog(Context context) {
        super(context, R.style.commonDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        initView();
    }

    private void initView() {
        ll_common_dialog_title = (LinearLayout) findViewById(R.id.ll_common_dialog_title);
        tv_common_dialog_title = (TextView) findViewById(R.id.tv_common_dialog_title);
        tv_common_dialog_msg = (TextView) findViewById(R.id.tv_common_dialog_msg);
        ll_common_dialog_negativeButton = (LinearLayout) findViewById(R.id.ll_common_dialog_negativeButton);
        ll_common_dialog_nutralButton = (LinearLayout) findViewById(R.id.ll_common_dialog_nutralButton);
        ll_common_dialog_positiveButton = (LinearLayout) findViewById(R.id.ll_common_dialog_positiveButton);
        btn_common_dialog_negativeButton = (Button) findViewById(R.id.btn_common_dialog_negativeButton);
        btn_common_dialog_nutralButton = (Button) findViewById(R.id.btn_common_dialog_nutralButton);
        btn_common_dialog_positiveButton = (Button) findViewById(R.id.btn_common_dialog_positiveButton);
        if (!TextUtils.isEmpty(title)) {
            ll_common_dialog_title.setVisibility(View.VISIBLE);
            tv_common_dialog_title.setText(title);
        }
        if (!TextUtils.isEmpty(msg)) {
            tv_common_dialog_msg.setText(msg);
        }
        if (!TextUtils.isEmpty(positiveButton)) {
            ll_common_dialog_positiveButton.setVisibility(View.VISIBLE);
            btn_common_dialog_positiveButton.setText(positiveButton);
            btn_common_dialog_positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.onClick(CommonDialog.this);
                }
            });
        }
        if (!TextUtils.isEmpty(negativeButton)) {
            ll_common_dialog_negativeButton.setVisibility(View.VISIBLE);
            btn_common_dialog_negativeButton.setText(negativeButton);
            btn_common_dialog_negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.onClick(CommonDialog.this);
                }
            });
        }
        if (!TextUtils.isEmpty(nutralButton)) {
            ll_common_dialog_nutralButton.setVisibility(View.VISIBLE);
            btn_common_dialog_nutralButton.setText(negativeButton);
            btn_common_dialog_nutralButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.onClick(CommonDialog.this);
                }
            });
        }
    }

    public CommonDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CommonDialog setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public CommonDialog setPositiveButton(String positiveButton, OnClickListener click) {
        this.positiveButton = positiveButton;
        this.click = click;
        return this;
    }

    public CommonDialog setNegativeButton(String negativeButton, OnClickListener click) {
        this.negativeButton = negativeButton;
        this.click = click;
        return this;
    }

    public CommonDialog setNutralButton(String nutralButton, OnClickListener click) {
        this.nutralButton = nutralButton;
        this.click = click;
        return this;
    }


    public interface OnClickListener {
        void onClick(CommonDialog dialog);
    }

}
