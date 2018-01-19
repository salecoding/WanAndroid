package com.lw.wanandroid.ui.knowledgesystem;

import com.lw.wanandroid.base.BaseContract;

/**
 * Created by lw on 2018/1/19.
 */

public interface KnowledgeSystemContract {
    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<KnowledgeSystemContract.View> {
    }
}
