package com.lw.wanandroid.ui.hotsearch;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseFragment;
import com.lw.wanandroid.bean.Friend;
import com.lw.wanandroid.bean.HotKey;
import com.lw.wanandroid.constant.Constant;
import com.lw.wanandroid.ui.article.ArticleContentActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by lw on 2018/1/23.
 */

public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rvHots)
    RecyclerView mRvHots;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    public CommonHotAdapter mCommonHotAdapter;
    private TagFlowLayout mTtlBookMarks, mTflHotKeys, mTflHotFriends;
    private HotAdapter<HotKey> mHotKeyAdapter;
    private HotAdapter<Friend> mHotFriendAdapter, mBookMarkAdapter;
    private View mHotHeadView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        /**设置RecyclerView*/
        mRvHots.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvHots.setAdapter(mCommonHotAdapter);

        /**设置HotHeadView*/
        mHotHeadView = LayoutInflater.from(getContext()).inflate(R.layout.layout_hot_head, null);
        mTtlBookMarks = (TagFlowLayout) mHotHeadView.findViewById(R.id.tflBookMarks);
        mTflHotKeys = (TagFlowLayout) mHotHeadView.findViewById(R.id.tflHotKeys);
        mTflHotFriends = (TagFlowLayout) mHotHeadView.findViewById(R.id.tflHotFriends);
        mCommonHotAdapter.addHeaderView(mHotHeadView);

        /**设置监听*/
        setListener();

        /**请求数据*/
        mPresenter.loadHotData();
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void setHotData(List<HotKey> hotKeys, List<Friend> friends) {
        mHotKeyAdapter = new HotAdapter(getContext(), hotKeys);
        mTflHotKeys.setAdapter(mHotKeyAdapter);

        mHotFriendAdapter = new HotAdapter<>(getContext(), friends);
        mTflHotFriends.setAdapter(mHotFriendAdapter);

        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    public static HotFragment newInstance() {
        return new HotFragment();
    }

    private void setListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mTflHotKeys.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String name = mHotKeyAdapter.getItem(position).getName();
                ARouter.getInstance().build("/hotsearch/SearchActivity")
                        .withString(Constant.CONTENT_HOT_NAME_KEY, name)
                        .navigation();
                return false;
            }
        });
        mTflHotFriends.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ArticleContentActivity.start(mHotFriendAdapter.getItem(position).getId(),
                        mHotFriendAdapter.getItem(position).getLink(), mHotFriendAdapter.getItem(position).getName(),
                        null);
                return false;
            }
        });
    }
}
