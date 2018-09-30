package com.apathy.arouterlogin;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.apathy.arouterlogin.base.BaseActivity;
import com.apathy.arouterlogin.constants.ConfigConstants;


@Route(path = ConfigConstants.FIRST_PATH)
public class FirstActivity extends BaseActivity {

    private TextView tvMsg;

    @Autowired
    public String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
        tvMsg.setText(msg);

    }

    private void initView() {
        tvMsg = findViewById(R.id.tv_msg);
    }
}
