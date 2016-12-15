package com.example.library.rxbus;

import java.util.Objects;

/**
 * Created by Administrator on 2016/12/9.
 */

public class RxEvent {
    private int code;
    private Objects obj;

    public RxEvent(int code) {
        this.code = code;
    }

    public RxEvent(int code, Objects obj) {
        this.code = code;
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Objects getObj() {
        return obj;
    }

    public void setObj(Objects obj) {
        this.obj = obj;
    }
}
