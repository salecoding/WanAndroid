package com.lw.wanandroid.ui.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseFragment;
import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.bean.Banner;
import com.lw.wanandroid.net.GlideImageLoader;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by lw on 2018/1/18.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View,
        ArticleAdapter.OnItemClickListener, ArticleAdapter.OnItemChildClickListener {

    @BindView(R.id.rvHomeArticles)
    RecyclerView mRvHomeArticles;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    ArticleAdapter mArticleAdapter;
    private com.youth.banner.Banner mBannerAds;
    private View mHomeBannerHeadView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        /**设置RecyclerView*/
        mRvHomeArticles.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvHomeArticles.setAdapter(mArticleAdapter);

        /**设置BannerHeadView*/
        mHomeBannerHeadView = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_banner_head, null);
        mBannerAds = (com.youth.banner.Banner) mHomeBannerHeadView.findViewById(R.id.banner_ads);
        mArticleAdapter.addHeaderView(mHomeBannerHeadView);

        /**设置item点击事件*/
        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);

        /**请求数据*/
        mPresenter.loadHomeBanners();
        mPresenter.loadHomeArticles();
    }

    @Override
    public void setHomeBanners(List<Banner> banners) {
        List<String> images = new ArrayList();
        List<String> titles = new ArrayList();
        for (Banner banner : banners) {
            images.add(banner.getImagePath());
            titles.add(banner.getTitle());
        }
        mBannerAds.setImages(images)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();
        mBannerAds.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
            }
        });
    }

    @Override
    public void setHomeArticles(Article article) {
        mArticleAdapter.setNewData(article.getDatas());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.tvChapterName) {
        } else if (view.getId() == R.id.ivCollect) {
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

}
