package com.lw.wanandroid.ui.article;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseActivity;
import com.lw.wanandroid.bean.KnowledgeSystem;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lw on 2018/1/22.
 */
@Route(path = "/article/ArticleTypeActivity")
public class ArticleTypeActivity extends BaseActivity {
    @Autowired
    public String title;
    @Autowired
    public List<KnowledgeSystem.ChildrenBean> childrenData;
    @BindView(R.id.tabArticleTypes)
    TabLayout mTabArticleTypes;
    @BindView(R.id.vpArticleTypes)
    ViewPager mVpArticleTypes;

    ArticleTypeFragmentPagerAdapter mArticleTypeFragmentPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_type;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        setToolbarTitle(title);
        mArticleTypeFragmentPagerAdapter = new ArticleTypeFragmentPagerAdapter(getSupportFragmentManager(), childrenData);
        mVpArticleTypes.setAdapter(mArticleTypeFragmentPagerAdapter);
        mTabArticleTypes.setupWithViewPager(mVpArticleTypes);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
