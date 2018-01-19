package com.lw.wanandroid.ui.my;

import android.view.View;

import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseFragment;

/**
 * Created by lw on 2018/1/18.
 */

public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

    }

    public static MyFragment newInstance() {
        return new MyFragment();
    }
}
