package com.july.sample.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.example.library.location.BDLocationUtils;
import com.example.library.pay.AliPayUtils;
import com.example.library.tools.ToastUtils;
import com.example.library.widget.bottomdialog.ActionSheetDialog;
import com.example.library.widget.commondialog.CommonDialog;
import com.example.library.widget.progressdialog.ProgressDlg;
import com.july.sample.R;
import com.july.sample.base.BaseActivity;
import com.july.sample.presenter.MainActivityPresenter;
import com.july.sample.ui.test.SpringTest;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IMainActivity, MainActivityPresenter> implements IMainActivity {


    @Bind(R.id.btn_spring)
    Button btnSpring;
    @Bind(R.id.commondialog)
    Button commondialog;
    @Bind(R.id.progressdialog)
    Button progressdialog;
    @Bind(R.id.location)
    Button location;
    @Bind(R.id.okgo)
    Button okgo;
    @Bind(R.id.okgo_tv)
    TextView okgoTv;
    @Bind(R.id.pay)
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @OnClick({R.id.btn_spring, R.id.commondialog, R.id.progressdialog, R.id.location, R.id.okgo, R.id.pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_spring:
                SpringTest.start(this);
                break;
            case R.id.commondialog:
                new CommonDialog(this).setTitle("测试").setMsg("测试内容显示是否正确").setNegativeButton("取消", new CommonDialog.OnClickListener() {
                    @Override
                    public void onClick(CommonDialog dialog) {
                        Logger.i("点击了取消");
                        dialog.dismiss();
                    }
                }).show();
                break;
            case R.id.progressdialog:
                ProgressDlg.showDialog(this, "测试");
                break;
            case R.id.location:
                BDLocationUtils.getInstance().locationOnce(new BDLocationUtils.LocationFinishListener() {
                    @Override
                    public void locationFinish(BDLocation bdLocation) {
                        ToastUtils.showShortToast("定位：" + bdLocation.getAddrStr());
                    }
                });
                break;
            case R.id.okgo:
                mPresenter.getText();
                break;
            case R.id.pay:
                new ActionSheetDialog(this).builder()
                        .setTitle("选择支付方式")
                        .setCancelable(true)
                        .addSheetItem("支付宝", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                alipayTest("2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D");
                            }
                        })
                        .addSheetItem("微信", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                ToastUtils.showShortToast("微信");
                            }
                        }).show();
                break;
        }
    }


    /**
     * 支付宝支付
     */
    public void alipayTest(String orderInfo) {
        AliPayUtils.getInstance(this).pay(orderInfo);
    }

    @Override
    public void showProgressDlg() {
        ProgressDlg.showDialog(this, "OKGO请求测试", false);
    }

    @Override
    public void dismissProgressDlg() {
        ProgressDlg.dismissDialog();
    }

    @Override
    public void showOKGOData(String text) {
        okgoTv.setText(text);
    }
}
