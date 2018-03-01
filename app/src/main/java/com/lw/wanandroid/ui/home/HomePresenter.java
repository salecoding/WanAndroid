package com.lw.wanandroid.ui.home;

import com.blankj.utilcode.util.SPUtils;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.App;
import com.lw.wanandroid.base.BasePresenter;
import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.bean.Banner;
import com.lw.wanandroid.bean.DataResponse;
import com.lw.wanandroid.bean.User;
import com.lw.wanandroid.constant.Constant;
import com.lw.wanandroid.constant.LoadType;
import com.lw.wanandroid.net.ApiService;
import com.lw.wanandroid.net.RetrofitManager;
import com.lw.wanandroid.utils.ArticleUtils;
import com.lw.wanandroid.utils.RxSchedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

/**
 * Created by lw on 2018/1/18.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    private int mPage;
    private boolean mIsRefresh;

    @Inject
    public HomePresenter() {
        this.mIsRefresh = true;
    }

    @Override
    public void loadHomeBanners() {
        RetrofitManager.create(ApiService.class)
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
        RetrofitManager.create(ApiService.class)
                .getHomeArticles(mPage)
                .compose(RxSchedulers.<DataResponse<Article>>applySchedulers())
                .compose(mView.<DataResponse<Article>>bindToLife())
                .subscribe(new Consumer<DataResponse<Article>>() {
                    @Override
                    public void accept(DataResponse<Article> dataResponse) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                        mView.setHomeArticles(dataResponse.getData(), loadType);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_ERROR : LoadType.TYPE_LOAD_MORE_ERROR;
                        mView.setHomeArticles(new Article(), loadType);
                    }
                });
    }

    @Override
    public void refresh() {
        mPage = 0;
        mIsRefresh = true;
        loadHomeBanners();
        loadHomeArticles();
    }

    @Override
    public void loadMore() {
        mPage++;
        mIsRefresh = false;
        loadHomeArticles();
    }

    @Override
    public void collectArticle(final int position, final Article.DatasBean bean) {
        ArticleUtils.collectArticle(mView, position, bean);
    }

    @Override
    public void loadHomeData() {
        mView.showLoading();
        String username = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.USERNAME_KEY);
        String password = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.PASSWORD_KEY);
        Observable<DataResponse<User>> observableUser = RetrofitManager.create(ApiService.class).login(username, password);
        Observable<DataResponse<List<Banner>>> observableBanner = RetrofitManager.create(ApiService.class).getHomeBanners();
        Observable<DataResponse<Article>> observableArticle = RetrofitManager.create(ApiService.class).getHomeArticles(mPage);
        Observable.zip(observableUser, observableBanner, observableArticle, new Function3<DataResponse<User>, DataResponse<List<Banner>>, DataResponse<Article>, Map<String, Object>>() {
            @Override
            public Map<String, Object> apply(DataResponse<User> response, DataResponse<List<Banner>> dataResponse, DataResponse<Article> dataResponse2) throws Exception {
                Map<String, Object> objMap = new HashMap<>();
                objMap.put(Constant.USER_KEY, response);
                objMap.put(Constant.BANNER_KEY, dataResponse.getData());
                objMap.put(Constant.ARTICLE_KEY, dataResponse2.getData());
                return objMap;
            }
        }).compose(RxSchedulers.<Map<String, Object>>applySchedulers()).compose(mView.<Map<String, Object>>bindToLife()).subscribe(new Consumer<Map<String, Object>>() {
            @Override
            public void accept(Map<String, Object> map) throws Exception {
                DataResponse<User> dataResponse = (DataResponse<User>) map.get(Constant.USER_KEY);
                if (dataResponse.getErrorCode() == 0) {
                    mView.showSuccess(App.getAppContext().getString(R.string.auto_login_success));
                } else {
                    mView.showFaild(String.valueOf(dataResponse.getErrorMsg()));
                }
                List<Banner> banners = (List<Banner>) map.get(Constant.BANNER_KEY);
                Article article = (Article) map.get(Constant.ARTICLE_KEY);
                mView.setHomeBanners(banners);
                mView.setHomeArticles(article, LoadType.TYPE_REFRESH_SUCCESS);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.showFaild(throwable.getMessage());
            }
        });
    }
}
