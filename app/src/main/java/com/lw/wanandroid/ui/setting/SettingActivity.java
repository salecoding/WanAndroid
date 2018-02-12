package com.lw.wanandroid.ui.setting;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseActivity;

/**
 * Created by lw on 2018/1/25.
 */
@Route(path = "/setting/SettingActivity")
public class SettingActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_fragment, SettingFragment.newInstance())
                .commit();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
