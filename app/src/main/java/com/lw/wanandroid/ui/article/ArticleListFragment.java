package com.lw.wanandroid.ui.article;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseFragment;
import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.event.LoginEvent;
import com.lw.wanandroid.utils.RxBus;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/1/22.
 */
@Route(path = "/article/ArticleListFragment")
public class ArticleListFragment extends BaseFragment<ArticleListPresenter> implements ArticleListContract.View, ArticleAdapter.OnItemClickListener, ArticleAdapter.OnItemChildClickListener,
        SwipeRefreshLayout.OnRefreshListener, ArticleAdapter.RequestLoadMoreListener {
    @BindView(R.id.rvArticleList)
    RecyclerView mRvArticleList;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Autowired
    public int cid;
    @Inject
    ArticleAdapter mArticleAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_article_list;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        /**隐藏文章类型*/
        mArticleAdapter.setChapterNameVisible(false);

        /**设置RecyclerView*/
        mRvArticleList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvArticleList.setAdapter(mArticleAdapter);

        /**设置事件监听*/
        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mArticleAdapter.setOnLoadMoreListener(this);

        /**请求数据*/
        mPresenter.loadKnowledgeSystemArticles(cid);

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
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.ivCollect) {
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
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }

    @Override
    public void setKnowledgeSystemArticles(Article article, int loadType) {
        setLoadDataResult(mArticleAdapter, mSwipeRefreshLayout, article.getDatas(), loadType);
    }

    @Override
    public void collectArticleSuccess(int position, Article.DatasBean bean) {
        mArticleAdapter.setData(position, bean);
    }
}
