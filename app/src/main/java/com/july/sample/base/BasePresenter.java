package com.july.sample.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/12/14.
 */

public abstract class BasePresenter<T> {

    protected Reference<T> mViewRef;//View接口类型弱引用

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view); //建立关联
    }

    protected T getView() {
        return mViewRef.get();//获取View
    }

    public boolean isViewAttached() {//判断是否与View建立了关联
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {//解除关联
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
