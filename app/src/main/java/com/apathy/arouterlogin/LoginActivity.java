package com.apathy.arouterlogin;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.apathy.arouterlogin.base.BaseActivity;
import com.apathy.arouterlogin.constants.ConfigConstants;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

@Route(path = ConfigConstants.LOGIN_PATH)
public class LoginActivity extends BaseActivity {


    @Autowired
    public String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_login).setOnClickListener(v -> {
            SPUtils.getInstance().put(ConfigConstants.SP_IS_LOGIN, true);

            ToastUtils.showShort("登录成功");
            if (!StringUtils.isEmpty(path)) {
                ARouter.getInstance().build(path)
                        .with(getIntent().getExtras())
                        .navigation();
            }
            finish();
        });

    }


}
