package com.lw.wanandroid.ui.my;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseActivity;

/**
 * Created by lw on 2018/1/24.
 */
@Route(path = "/my/RegisterActivity")
public class RegisterActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
