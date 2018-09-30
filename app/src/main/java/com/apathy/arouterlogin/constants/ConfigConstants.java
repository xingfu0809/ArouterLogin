package com.apathy.arouterlogin.constants;

import com.apathy.arouterlogin.BuildConfig;

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
public final class ConfigConstants {

    public static final boolean IS_DEBUG = BuildConfig.IS_DEBUG;


    public static final String TAG = "TAG";

    public static final String PATH = "path";

    //存储是否登录的
    public static final String SP_IS_LOGIN = "sp_is_login";

    private static final String BASE_PATH = "/base/path/";

    //登录
    public static final String LOGIN_PATH = BASE_PATH + "login";
    //不需要登录的activity
    public static final String FIRST_PATH = BASE_PATH + "first";
    //登录登录的actvity
    public static final String SECOND_PATH = BASE_PATH + "second";
}
