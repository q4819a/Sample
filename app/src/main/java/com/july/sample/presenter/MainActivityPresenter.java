package com.july.sample.presenter;

import com.example.library.tools.ToastUtils;
import com.july.sample.api.JulyApi;
import com.july.sample.base.BasePresenter;
import com.july.sample.ui.IMainActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MainActivityPresenter extends BasePresenter<IMainActivity> {


    public void getText() {
        OkGo.get(JulyApi.TEST)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        getView().showProgressDlg();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        getView().dismissProgressDlg();
                        getView().showOKGOData(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().dismissProgressDlg();
                        ToastUtils.showShortToast("获取数据失败:" + throwable.getMessage());
                    }
                });
    }


}
