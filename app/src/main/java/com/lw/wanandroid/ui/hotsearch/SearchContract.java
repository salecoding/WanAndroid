package com.lw.wanandroid.ui.hotsearch;

import com.lw.wanandroid.base.BaseContract;
import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.constant.LoadType;

/**
 * Created by lw on 2018/1/23.
 */

public interface SearchContract {
    interface View extends BaseContract.BaseView {
        void setSearchArtcles(Article article, @LoadType.checker int loadType);

        void collectArticleSuccess(int position, Article.DatasBean bean);

    }

    interface Presenter extends BaseContract.BasePresenter<SearchContract.View> {
        void loadSearchArtcles(String k);

        void refresh();

        void loadMore();

        void collectArticle(int position, Article.DatasBean bean);
    }
}
