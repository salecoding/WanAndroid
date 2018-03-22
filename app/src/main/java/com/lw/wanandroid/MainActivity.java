package com.lw.wanandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.lw.wanandroid.base.BaseActivity;
import com.lw.wanandroid.ui.home.HomeFragment;
import com.lw.wanandroid.ui.hotsearch.HotFragment;
import com.lw.wanandroid.ui.knowledgesystem.KnowledgeSystemFragment;
import com.lw.wanandroid.ui.my.MyFragment;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

@Route(path = "/wanandroid/MainActivity")
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    private ISupportFragment[] mFragments = new ISupportFragment[4];
    private long mExitTime;
    private int preIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        mNavigation.setOnNavigationItemSelectedListener(this);
        ISupportFragment homeFragment = findFragment(HomeFragment.class);
        if (homeFragment == null) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = KnowledgeSystemFragment.newInstance();
            mFragments[2] = MyFragment.newInstance();
            mFragments[3] = HotFragment.newInstance();
            loadMultipleRootFragment(R.id.layout_fragment, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);
        } else {
            // 这里我们需要拿到mFragments的引用
            mFragments[0] = homeFragment;
            mFragments[1] = findFragment(KnowledgeSystemFragment.class);
            mFragments[2] = findFragment(MyFragment.class);
            mFragments[3] = findFragment(HotFragment.class);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mToolbar.setTitle(R.string.app_name);
                showHideFragment(mFragments[0], mFragments[preIndex]);
                preIndex = 0;
                break;
            case R.id.navigation_knowledgesystem:
                mToolbar.setTitle(R.string.title_knowledgesystem);
                showHideFragment(mFragments[1], mFragments[preIndex]);
                preIndex = 1;
                break;
            case R.id.navigation_my:
                mToolbar.setTitle(R.string.title_my);
                showHideFragment(mFragments[2], mFragments[preIndex]);
                preIndex = 2;
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuHot) {
            mToolbar.setTitle(R.string.hot_title);
            showHideFragment(mFragments[3], mFragments[preIndex]);
            preIndex = 3;
        } else if (item.getItemId() == R.id.menuSearch) {
            ARouter.getInstance().build("/hotsearch/SearchActivity").navigation();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShort(R.string.exit_system);
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
