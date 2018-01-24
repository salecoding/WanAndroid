package com.lw.wanandroid.ui.article;

import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by lw on 2018/1/22.
 */
@Route(path = "/article/ArticleContentActivity")
public class ArticleContentActivity extends BaseActivity {
    @Autowired
    public int id;
    @Autowired
    public String url;
    @Autowired
    public String title;
    @BindView(R.id.webContent)
    FrameLayout mWebContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_content;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(mWebContent, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(mReceivedTitleCallback) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    private ChromeClientCallbackManager.ReceivedTitleCallback mReceivedTitleCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            setToolbarTitle(title);
        }
    };
}
