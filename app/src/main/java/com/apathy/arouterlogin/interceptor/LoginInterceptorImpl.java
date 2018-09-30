package com.apathy.arouterlogin.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.apathy.arouterlogin.constants.ConfigConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;

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

@Interceptor(name = "login", priority = 6)
public class LoginInterceptorImpl implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String path = postcard.getPath();
        LogUtils.d(path);
        boolean isLogin = SPUtils.getInstance().getBoolean(ConfigConstants.SP_IS_LOGIN, false);

        if (isLogin) { // 如果已经登录不拦截
            callback.onContinue(postcard);
        } else {  // 如果没有登录
            switch (path) {
                // 不需要登录的直接进入这个页面
                case ConfigConstants.LOGIN_PATH:
                case ConfigConstants.FIRST_PATH:
                    callback.onContinue(postcard);
                    break;
                default:
                    callback.onInterrupt(null);
                    // 需要登录的直接拦截下来
                    break;
            }
        }

    }

    @Override
    public void init(Context context) {
        LogUtils.v("路由登录拦截器初始化成功"); //只会走一次
    }

}
