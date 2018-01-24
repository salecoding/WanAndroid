package com.lw.wanandroid.ui.knowledgesystem;

import com.lw.wanandroid.base.BaseContract;
import com.lw.wanandroid.bean.KnowledgeSystem;

import java.util.List;

/**
 * Created by lw on 2018/1/19.
 */

public interface KnowledgeSystemContract {
    interface View extends BaseContract.BaseView {
        void setKnowledgeSystems(List<KnowledgeSystem> knowledgeSystems);
    }

    interface Presenter extends BaseContract.BasePresenter<KnowledgeSystemContract.View> {
        void loadKnowledgeSystems();

        void refresh();
    }
}
