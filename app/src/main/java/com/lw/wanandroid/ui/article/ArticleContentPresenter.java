package com.lw.wanandroid.ui.article;

import com.blankj.utilcode.util.SPUtils;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.App;
import com.lw.wanandroid.base.BasePresenter;
import com.lw.wanandroid.bean.DataResponse;
import com.lw.wanandroid.constant.Constant;
import com.lw.wanandroid.net.ApiService;
import com.lw.wanandroid.net.RetrofitManager;
import com.lw.wanandroid.ui.my.LoginActivity;
import com.lw.wanandroid.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/1/25.
 */

public class ArticleContentPresenter extends BasePresenter<ArticleContentContract.View> implements ArticleContentContract.Presenter {
    @Inject
    public ArticleContentPresenter() {
    }

    @Override
    public void collectArticle(int id) {
        if (SPUtils.getInstance(Constant.SHARED_NAME).getBoolean(Constant.LOGIN_KEY)) {
            RetrofitManager.create(ApiService.class)
                    .addCollectArticle(id)
                    .compose(RxSchedulers.<DataResponse>applySchedulers())
                    .compose(mView.<DataResponse>bindToLife())
                    .subscribe(new Consumer<DataResponse>() {
                        @Override
                        public void accept(DataResponse response) throws Exception {
                            if (response.getErrorCode() == 0) {
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
        } else {
            LoginActivity.start();
        }
    }

    @Override
    public void collectOutsideArticle(String title, String author, String link) {
        if (SPUtils.getInstance(Constant.SHARED_NAME).getBoolean(Constant.LOGIN_KEY)) {
            RetrofitManager.create(ApiService.class)
                    .addCollectOutsideArticle(title, author, link)
                    .compose(RxSchedulers.<DataResponse>applySchedulers())
                    .compose(mView.<DataResponse>bindToLife())
                    .subscribe(new Consumer<DataResponse>() {
                        @Override
                        public void accept(DataResponse response) throws Exception {
                            if (response.getErrorCode() == 0) {
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
        } else {
            LoginActivity.start();
        }
    }
}
