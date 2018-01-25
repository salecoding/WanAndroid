package com.lw.wanandroid.ui.article;

import com.blankj.utilcode.util.SPUtils;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.App;
import com.lw.wanandroid.base.BasePresenter;
import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.bean.DataResponse;
import com.lw.wanandroid.constant.Constant;
import com.lw.wanandroid.constant.LoadType;
import com.lw.wanandroid.net.ApiService;
import com.lw.wanandroid.net.RetrofitManager;
import com.lw.wanandroid.utils.RxSchedulers;
import com.lw.wanandroid.ui.my.LoginActivity;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/1/23.
 */

public class ArticleListPresenter extends BasePresenter<ArticleListContract.View> implements ArticleListContract.Presenter {

    private boolean mIsRefresh;
    private int mPage, mCid;

    @Inject
    public ArticleListPresenter() {
        this.mIsRefresh = true;
    }

    @Override
    public void loadKnowledgeSystemArticles(int cid) {
        this.mCid = cid;
        RetrofitManager.create(ApiService.class).getKnowledgeSystemArticles(mPage, mCid)
                .compose(RxSchedulers.<DataResponse<Article>>applySchedulers())
                .compose(mView.<DataResponse<Article>>bindToLife())
                .subscribe(new Consumer<DataResponse<Article>>() {
                    @Override
                    public void accept(DataResponse<Article> dataResponse) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                        mView.setKnowledgeSystemArticles(dataResponse.getData(), loadType);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.setKnowledgeSystemArticles(new Article(), loadType);
                    }
                });
    }

    @Override
    public void refresh() {
        mPage = 0;
        mIsRefresh = true;
        loadKnowledgeSystemArticles(mCid);
    }

    @Override
    public void loadMore() {
        mPage++;
        mIsRefresh = false;
        loadKnowledgeSystemArticles(mCid);
    }

    @Override
    public void collectArticle(final int position, final Article.DatasBean bean) {
        if (SPUtils.getInstance(Constant.SHARED_NAME).getBoolean(Constant.LOGIN_KEY)) {
            if (bean.isCollect()) {
                RetrofitManager.create(ApiService.class).removeCollectArticle(bean.getId(), -1)
                        .compose(RxSchedulers.<DataResponse>applySchedulers())
                        .compose(mView.<DataResponse>bindToLife())
                        .subscribe(new Consumer<DataResponse>() {
                            @Override
                            public void accept(DataResponse response) throws Exception {
                                if (response.getErrorCode() == 0) {
                                    bean.setCollect(!bean.isCollect());
                                    mView.collectArticleSuccess(position, bean);
                                    mView.showSuccess(App.getAppContext().getString(R.string.collection_cancel_success));
                                } else {
                                    mView.showFaild(App.getAppContext().getString(R.string.collection_cancel_failed, response.getData()));
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mView.showFaild(throwable.getMessage());
                            }
                        });
            } else {
                RetrofitManager.create(ApiService.class).addCollectArticle(bean.getId())
                        .compose(RxSchedulers.<DataResponse>applySchedulers())
                        .compose(mView.<DataResponse>bindToLife())
                        .subscribe(new Consumer<DataResponse>() {
                            @Override
                            public void accept(DataResponse response) throws Exception {
                                if (response.getErrorCode() == 0) {
                                    bean.setCollect(!bean.isCollect());
                                    mView.collectArticleSuccess(position, bean);
                                    mView.showSuccess(App.getAppContext().getString(R.string.collection_success));
                                } else {
                                    mView.showFaild(App.getAppContext().getString(R.string.collection_failed, response.getErrorMsg()));
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mView.showFaild(throwable.getMessage());
                            }
                        });
            }
        } else {
            LoginActivity.start();
        }
    }
}
