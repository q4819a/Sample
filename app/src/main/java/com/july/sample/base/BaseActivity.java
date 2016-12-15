package com.july.sample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.library.base.LibBaseActivity;

/**
 * Created by Administrator on 2016/12/12.
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends LibBaseActivity {

    protected T mPresenter;//Presenter对象

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//创建Presenter
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract T createPresenter();

}