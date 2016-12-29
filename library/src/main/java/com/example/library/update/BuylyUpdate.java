package com.example.library.update;

import com.tencent.bugly.beta.Beta;

/**
 * Created by Administrator on 2016/12/27.
 */

public class BuylyUpdate implements UpdateType {


    @Override
    public void checkUpgrade() {
        Beta.checkUpgrade();
    }
}
