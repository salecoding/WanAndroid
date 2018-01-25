package com.lw.wanandroid.ui.my;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseActivity;

/**
 * Created by lw on 2018/1/25.
 */
@Route(path = "/my/MyBookmarkActivity")
public class MyBookmarkActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_bookmark;
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
