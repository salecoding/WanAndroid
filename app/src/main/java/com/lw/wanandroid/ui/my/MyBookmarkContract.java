package com.lw.wanandroid.ui.my;

import com.lw.wanandroid.base.BaseContract;
import com.lw.wanandroid.bean.Friend;

import java.util.List;

/**
 * Created by lw on 2018/2/2.
 */

public interface MyBookmarkContract {
    interface View extends BaseContract.BaseView {
        void setMyBookmarks(List<Friend> bookmarks);
    }

    interface Presenter extends BaseContract.BasePresenter<MyBookmarkContract.View> {
        void loadMyBookmarks();

        void editBookmark(int id, String name, String link);

        void delBookmark(int id);

        void refresh();
    }
}
