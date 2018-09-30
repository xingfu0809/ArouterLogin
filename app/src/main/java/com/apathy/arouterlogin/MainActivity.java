package com.apathy.arouterlogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.apathy.arouterlogin.base.BaseActivity;
import com.apathy.arouterlogin.constants.ConfigConstants;
import com.apathy.arouterlogin.interceptor.LoginNavigationCallbackImpl;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button mBtnLogin = findViewById(R.id.btn_login);
        Button mBtnFirst = findViewById(R.id.btn_first);
        Button mBtnSecond = findViewById(R.id.btn_second);
        findViewById(R.id.btn_exit).setOnClickListener(view -> {
            ToastUtils.showShort("退出登录成功");

            SPUtils.getInstance().remove(ConfigConstants.SP_IS_LOGIN);

        });
        mBtnLogin.setOnClickListener(this);
        mBtnFirst.setOnClickListener(this);
        mBtnSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                ARouter.getInstance().build(ConfigConstants.LOGIN_PATH)
                        .navigation();

                break;
            case R.id.btn_first: // 不需要登录的
                ARouter.getInstance().build(ConfigConstants.FIRST_PATH)
                        .withString("msg", "ARouter传递过来的不需要登录的参数msg")
                        .navigation();

                break;
            case R.id.btn_second: // 需要登录的
                ARouter.getInstance().build(ConfigConstants.SECOND_PATH)
                        .withString("msg", "ARouter传递过来的需要登录的参数msg")
                        .navigation(this,new LoginNavigationCallbackImpl());
                break;
        }
    }
}
