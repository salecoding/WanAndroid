package com.lw.wanandroid.ui.home;

import com.lw.wanandroid.base.BaseContract;
import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.bean.Banner;

import java.util.List;

/**
 * Created by lw on 2018/1/18.
 */

public class HomeContract {

    public interface View extends BaseContract.BaseView {
        void setHomeBanners(List<Banner> banners);
        void setHomeArticles(Article article);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void loadHomeBanners();
        void loadHomeArticles();
    }
}
