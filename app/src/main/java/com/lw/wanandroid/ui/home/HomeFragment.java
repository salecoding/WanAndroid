package com.lw.wanandroid.ui.home;

import android.os.Bundle;
import android.view.View;

import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseFragment;

/**
 * Created by lw on 2018/1/18.
 */

public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
}
