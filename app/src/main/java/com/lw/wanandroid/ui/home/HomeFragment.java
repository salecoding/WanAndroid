package com.lw.wanandroid.ui.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseFragment;
import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.bean.Banner;
import com.lw.wanandroid.bean.KnowledgeSystem;
import com.lw.wanandroid.constant.Constant;
import com.lw.wanandroid.constant.LoadType;
import com.lw.wanandroid.event.LoginEvent;
import com.lw.wanandroid.ui.article.ArticleAdapter;
import com.lw.wanandroid.ui.article.ArticleContentActivity;
import com.lw.wanandroid.utils.GlideImageLoader;
import com.lw.wanandroid.utils.RxBus;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/1/18.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, ArticleAdapter.OnItemClickListener, ArticleAdapter.OnItemChildClickListener,
        SwipeRefreshLayout.OnRefreshListener, ArticleAdapter.RequestLoadMoreListener {
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

        /**设置事件监听*/
        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mArticleAdapter.setOnLoadMoreListener(this);

        /**请求数据*/
        mPresenter.loadHomeData();

        /**登陆成功刷新*/
        RxBus.getInstance().toFlowable(LoginEvent.class)
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent event) throws Exception {
                        mPresenter.refresh();
                    }
                });
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showFaild(String errorMsg) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setHomeBanners(final List<Banner> banners) {
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
                ArticleContentActivity.start(banners.get(position).getId(), banners.get(position).getUrl(),
                        banners.get(position).getTitle(), null);
            }
        });
    }

    @Override
    public void setHomeArticles(Article article, @LoadType.checker int loadType) {
        setLoadDataResult(mArticleAdapter, mSwipeRefreshLayout, article.getDatas(), loadType);
    }

    @Override
    public void collectArticleSuccess(int position, Article.DatasBean bean) {
        mArticleAdapter.setData(position, bean);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.tvChapterName) {
            List<KnowledgeSystem.ChildrenBean> childrenBeans = new ArrayList<>();
            childrenBeans.add(new KnowledgeSystem.ChildrenBean(mArticleAdapter.getItem(position).getChapterId(),
                    mArticleAdapter.getItem(position).getChapterName()));
            ARouter.getInstance().build("/article/ArticleTypeActivity")
                    .withString(Constant.CONTENT_TITLE_KEY, mArticleAdapter.getItem(position).getChapterName())
                    .withObject(Constant.CONTENT_CHILDREN_DATA_KEY, childrenBeans)
                    .navigation();
        } else if (view.getId() == R.id.ivCollect) {
            mPresenter.collectArticle(position, mArticleAdapter.getItem(position));
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArticleContentActivity.start(mArticleAdapter.getItem(position).getId(),
                mArticleAdapter.getItem(position).getLink(), mArticleAdapter.getItem(position).getTitle(),
                mArticleAdapter.getItem(position).getAuthor());
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
}
