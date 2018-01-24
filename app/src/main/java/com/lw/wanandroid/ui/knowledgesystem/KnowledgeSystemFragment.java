package com.lw.wanandroid.ui.knowledgesystem;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseFragment;
import com.lw.wanandroid.bean.KnowledgeSystem;
import com.lw.wanandroid.constant.Constant;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by lw on 2018/1/18.
 */

public class KnowledgeSystemFragment extends BaseFragment<KnowledgeSystemPresenter> implements KnowledgeSystemContract.View,
        KnowledgeSystemAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rvKnowledgeSystems)
    RecyclerView mRvKnowledgeSystems;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    KnowledgeSystemAdapter mKnowledgeSystemAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_system;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        /**设置RecyclerView*/
        mRvKnowledgeSystems.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvKnowledgeSystems.setAdapter(mKnowledgeSystemAdapter);

        /**设置事件监听*/
        mKnowledgeSystemAdapter.setOnItemClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        /**请求数据*/
        mPresenter.loadKnowledgeSystems();
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void setKnowledgeSystems(List<KnowledgeSystem> knowledgeSystems) {
        mKnowledgeSystemAdapter.setNewData(knowledgeSystems);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ARouter.getInstance().build("/article/ArticleTypeActivity")
                .withString(Constant.CONTENT_TITLE_KEY, mKnowledgeSystemAdapter.getItem(position).getName())
                .withObject(Constant.CONTENT_CHILDREN_DATA_KEY, mKnowledgeSystemAdapter.getItem(position).getChildren())
                .navigation();
    }

    public static KnowledgeSystemFragment newInstance() {
        return new KnowledgeSystemFragment();
    }

}
