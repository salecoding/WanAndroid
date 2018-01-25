package com.lw.wanandroid.ui.hotsearch;

import com.lw.wanandroid.base.BasePresenter;
import com.lw.wanandroid.bean.DataResponse;
import com.lw.wanandroid.bean.Friend;
import com.lw.wanandroid.bean.HotKey;
import com.lw.wanandroid.constant.Constant;
import com.lw.wanandroid.net.ApiService;
import com.lw.wanandroid.net.RetrofitManager;
import com.lw.wanandroid.utils.RxSchedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/1/23.
 */

public class HotPresenter extends BasePresenter<HotContract.View> implements HotContract.Presenter {
    @Inject
    public HotPresenter() {
    }

    @Override
    public void loadHotData() {
        mView.showLoading();
        Observable<DataResponse<List<Friend>>> observableFriend = RetrofitManager.create(ApiService.class).getHotFriends();
        Observable<DataResponse<List<HotKey>>> observableHotKey = RetrofitManager.create(ApiService.class).getHotKeys();
        Observable.zip(observableFriend, observableHotKey, new BiFunction<DataResponse<List<Friend>>, DataResponse<List<HotKey>>, Map<String, Object>>() {
            @Override
            public Map<String, Object> apply(DataResponse<List<Friend>> response, DataResponse<List<HotKey>> response2) throws Exception {
                Map<String, Object> objMap = new HashMap<>();
                objMap.put(Constant.CONTENT_HOT_KEY, response2.getData());
                objMap.put(Constant.CONTENT_HOT_FRIEND_KEY, response.getData());
                return objMap;
            }
        }).compose(RxSchedulers.<Map<String, Object>>applySchedulers()).compose(mView.<Map<String, Object>>bindToLife()).subscribe(new Consumer<Map<String, Object>>() {
            @Override
            public void accept(Map<String, Object> map) throws Exception {
                List<HotKey> hotKeys = (List<HotKey>) map.get(Constant.CONTENT_HOT_KEY);
                List<Friend> friends = (List<Friend>) map.get(Constant.CONTENT_HOT_FRIEND_KEY);
                mView.setHotData(hotKeys, friends);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.showFaild(throwable.getMessage());
            }
        });
    }

    @Override
    public void refresh() {
        loadHotData();
    }
}
