package com.lw.wanandroid.ui.my;

import com.lw.wanandroid.base.BasePresenter;
import com.lw.wanandroid.bean.DataResponse;
import com.lw.wanandroid.bean.Friend;
import com.lw.wanandroid.net.ApiService;
import com.lw.wanandroid.net.RetrofitManager;
import com.lw.wanandroid.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/2/2.
 */

public class MyBookmarkPresenter extends BasePresenter<MyBookmarkContract.View> implements MyBookmarkContract.Presenter {
    private boolean mIsRefresh;

    @Inject
    public MyBookmarkPresenter() {
        this.mIsRefresh = true;
    }

    @Override
    public void loadMyBookmarks() {
        mView.showLoading();
        RetrofitManager.create(ApiService.class)
                .getBookmarks()
                .compose(RxSchedulers.<DataResponse<List<Friend>>>applySchedulers())
                .compose(mView.<DataResponse<List<Friend>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<Friend>>>() {
                    @Override
                    public void accept(DataResponse<List<Friend>> response) throws Exception {
                        mView.setMyBookmarks(response.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }

    @Override
    public void editBookmark(int id, String name, String link) {

    }

    @Override
    public void delBookmark(int id) {

    }

    @Override
    public void refresh() {
        mIsRefresh = true;
        loadMyBookmarks();
    }
}
