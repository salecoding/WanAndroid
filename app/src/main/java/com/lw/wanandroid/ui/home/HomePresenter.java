package com.lw.wanandroid.ui.home;

import com.lw.wanandroid.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by lw on 2018/1/18.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter() {
    }

    @Override
    public void loadArticleList() {
    }
}
