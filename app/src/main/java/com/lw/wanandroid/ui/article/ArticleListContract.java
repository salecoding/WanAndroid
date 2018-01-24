package com.lw.wanandroid.ui.article;

import com.lw.wanandroid.base.BaseContract;
import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.constant.LoadType;

/**
 * Created by lw on 2018/1/23.
 */

public interface ArticleListContract {
    interface View extends BaseContract.BaseView {

        void setKnowledgeSystemArticles(Article article, @LoadType.checker int loadType);

        void collectArticleSuccess(int position, Article.DatasBean bean);

    }

    interface Presenter extends BaseContract.BasePresenter<ArticleListContract.View> {
        void loadKnowledgeSystemArticles(int cid);

        void refresh();

        void loadMore();

        void collectArticle(int position, Article.DatasBean bean);

    }
}
