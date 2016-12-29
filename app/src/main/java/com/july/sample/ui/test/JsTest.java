package com.july.sample.ui.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.Button;

import com.example.library.widget.progressdialog.ProgressDlg;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.july.sample.R;
import com.july.sample.base.BaseActivity;
import com.july.sample.base.BasePresenter;


/**
 * Created by Administrator on 2016/12/21.
 */

public class JsTest extends BaseActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";
    BridgeWebView webView;
    Button button;

    public static void start(Context context) {
        Intent starter = new Intent(context, JsTest.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jstest);

        webView = (BridgeWebView) findViewById(R.id.webView);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);

        webView.setDefaultHandler(new DefaultHandler());

        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl("file:///android_asset/WebViewTest.html");

        webView.registerHandler("showDialog", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    showDialog(data);
                } else {
                    showDialog("测试");
                }
                function.onCallBack("1");
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    private void showDialog(String s) {
        ProgressDlg.showDialog(this, s);
    }

    @Override
    public void onClick(View v) {
        if (button.equals(v)) {
            webView.callHandler("showAlert", "测试", new CallBackFunction() {

                @Override
                public void onCallBack(String data) {
                    // TODO Auto-generated method stub
                    Log.i(TAG, "reponse data from js " + data);
                }

            });
        }

    }

}