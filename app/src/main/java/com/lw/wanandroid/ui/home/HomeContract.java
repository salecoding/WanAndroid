package com.lw.wanandroid.ui.home;

import com.lw.wanandroid.base.BaseContract;
import com.lw.wanandroid.bean.Article;

import java.util.List;

/**
 * Created by lw on 2018/1/18.
 */

public class HomeContract {

    interface View extends BaseContract.BaseView {
        void setArticleList(List<Article> aircleList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void loadArticleList();
    }
}
