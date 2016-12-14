package com.july.sample.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.example.library.location.BDLocationUtils;
import com.example.library.tools.ToastUtils;
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

    @OnClick({R.id.btn_spring, R.id.commondialog, R.id.progressdialog, R.id.location, R.id.okgo})
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
        }
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
