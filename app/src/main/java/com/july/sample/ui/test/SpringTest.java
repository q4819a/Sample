package com.july.sample.ui.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.library.widget.springview.container.RotationHeader;
import com.example.library.widget.springview.widget.SpringView;
import com.july.sample.R;
import com.july.sample.base.BaseActivity;
import com.july.sample.base.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12.
 */

public class SpringTest extends BaseActivity {


    @Bind(R.id.wv)
    WebView wv;
    @Bind(R.id.springview)
    SpringView springview;


    public static void start(Context context) {
        Intent starter = new Intent(context, SpringTest.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_test);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private void initView() {
        springview.setType(SpringView.Type.FOLLOW);
        springview.setHeader(new RotationHeader(this));
        wv.getSettings().setJavaScriptEnabled(true);
// 让webview对象支持解析alert()等特殊的javascript语句
        wv.setWebChromeClient(new WebChromeClient());
// 如果不使用该句代码，在点击超链地址后，会跳出程序，而弹出浏览器访问网页。
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("http://www.baidu.com");
    }


}
