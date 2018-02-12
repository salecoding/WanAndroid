package com.lw.wanandroid.ui.hotsearch;

import com.lw.wanandroid.base.BaseContract;
import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.constant.LoadType;
import com.lw.wanandroid.db.HistoryModel;

import java.util.List;

/**
 * Created by lw on 2018/1/23.
 */

public interface SearchContract {
    interface View extends BaseContract.BaseView {
        void setSearchArtcles(Article article, @LoadType.checker int loadType);

        void collectArticleSuccess(int position, Article.DatasBean bean);

        void setHistory(List<HistoryModel> historyModels);

        void addHistorySuccess(HistoryModel historyModel);
    }

    interface Presenter extends BaseContract.BasePresenter<SearchContract.View> {
        void loadSearchArtcles(String k);

        void refresh();

        void loadMore();

        void collectArticle(int position, Article.DatasBean bean);

        void loadHistory();

        void addHistory(String name);
    }
}
