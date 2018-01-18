package com.lw.wanandroid.ui.knowledgesystem;

import android.view.View;

import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseFragment;

/**
 * Created by lw on 2018/1/18.
 */

public class KnowledgeSystemFragment extends BaseFragment {
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

    }

    public static KnowledgeSystemFragment newInstance(){
        return new KnowledgeSystemFragment();
    }
}
