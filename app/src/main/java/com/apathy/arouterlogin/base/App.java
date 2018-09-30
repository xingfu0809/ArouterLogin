package com.apathy.arouterlogin.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.apathy.arouterlogin.constants.ConfigConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

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
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化阿里巴巴路由框架
        if (ConfigConstants.IS_DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        Utils.init(this);
        final LogUtils.Config config = LogUtils.getConfig()
                .setLogSwitch(ConfigConstants.IS_DEBUG)// 设置 log 总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(ConfigConstants.IS_DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(ConfigConstants.TAG)// 设置 log 全局标签，默认为空.
                .setSingleTagSwitch(false);// 一条日志仅输出一条，默认开，为美化 AS 3.1.0 的 Logcat`

    }
}
