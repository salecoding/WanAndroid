package com.lw.wanandroid.ui.home;

import com.lw.wanandroid.base.BasePresenter;
import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.bean.Banner;
import com.lw.wanandroid.bean.DataResponse;
import com.lw.wanandroid.net.HomeApiService;
import com.lw.wanandroid.net.RetrofitManager;
import com.lw.wanandroid.net.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/1/18.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter() {
    }

    @Override
    public void loadHomeBanners() {
        RetrofitManager.create(HomeApiService.class)
                .getHomeBanners()
                .compose(RxSchedulers.<DataResponse<List<Banner>>>applySchedulers())
                .compose(mView.<DataResponse<List<Banner>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<Banner>>>() {
                    @Override
                    public void accept(DataResponse<List<Banner>> dataResponse) throws Exception {
                        mView.setHomeBanners(dataResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }

    @Override
    public void loadHomeArticles() {
        RetrofitManager.create(HomeApiService.class)
                .getHomeArticles(1)
                .compose(RxSchedulers.<DataResponse<Article>>applySchedulers())
                .compose(mView.<DataResponse<Article>>bindToLife())
                .subscribe(new Consumer<DataResponse<Article>>() {
                    @Override
                    public void accept(DataResponse<Article> dataResponse) throws Exception {
                        mView.setHomeArticles(dataResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }
}
