package com.apathy.arouterlogin.interceptor;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.apathy.arouterlogin.constants.ConfigConstants;
import com.blankj.utilcode.util.LogUtils;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 * <pre>
 *     author: 梁幸福
 *     time  : 2018/9/30
 *     desc  :
 * </pre>
 */

public class LoginNavigationCallbackImpl  implements NavigationCallback{
    @Override //找到了
    public void onFound(Postcard postcard) {

    }

    @Override //找不到了
    public void onLost(Postcard postcard) {

    }

    @Override    //跳转成功了
    public void onArrival(Postcard postcard) {

    }

    @Override
    public void onInterrupt(Postcard postcard) {
        String path = postcard.getPath();
        LogUtils.v(path);
        Bundle bundle = postcard.getExtras();
        // 拦截了
        ARouter.getInstance().build(ConfigConstants.LOGIN_PATH)
                .with(bundle)
                .withString(ConfigConstants.PATH, path)
                .navigation();
    }
}
