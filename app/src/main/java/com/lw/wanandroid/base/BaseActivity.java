package com.lw.wanandroid.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lw.wanandroid.R;
import com.lw.wanandroid.di.component.ActivityComponent;
import com.lw.wanandroid.di.component.DaggerActivityComponent;
import com.lw.wanandroid.di.module.ActivityModule;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lw on 2018/1/18.
 */

public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends RxAppCompatActivity implements BaseContract.BaseView {
    @Nullable
    @Inject
    protected T mPresenter;
    protected ActivityComponent mActivityComponent;
    protected Toolbar mToolbar;
    private Unbinder unbinder;

    protected abstract int getLayoutId();

    protected abstract void initInjector();

    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        int layoutId = getLayoutId();
        setContentView(layoutId);
        initInjector();
        unbinder = ButterKnife.bind(this);
        initToolBar();
        initView();
        attachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        detachView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFaild(String errorMsg) {

    }

    @Override
    public void showNoNet() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                break;
        }
        return true;
    }

    /**
     * 初始化ActivityComponent
     */
    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    /**
     * 贴上view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 分离view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
