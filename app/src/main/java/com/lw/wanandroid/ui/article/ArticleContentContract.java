package com.lw.wanandroid.ui.article;

import com.lw.wanandroid.base.BaseContract;

import retrofit2.http.Field;

/**
 * Created by lw on 2018/1/25.
 */

public interface ArticleContentContract {
    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<ArticleContentContract.View> {
        void collectArticle(int id);

        void collectOutsideArticle(String title, String author, String link);
    }
}
