package com.lw.wanandroid.ui.hotsearch;

import com.lw.wanandroid.base.BaseContract;
import com.lw.wanandroid.bean.Friend;
import com.lw.wanandroid.bean.HotKey;

import java.util.List;

/**
 * Created by lw on 2018/1/23.
 */

public interface HotContract {
    interface View extends BaseContract.BaseView {
        void setHotData(List<HotKey> hotKeys, List<Friend> friends);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void loadHotData();

        void refresh();
    }
}
