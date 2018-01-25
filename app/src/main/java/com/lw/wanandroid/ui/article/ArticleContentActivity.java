package com.lw.wanandroid.ui.article;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseActivity;
import com.lw.wanandroid.constant.Constant;

import butterknife.BindView;

/**
 * Created by lw on 2018/1/22.
 */
@Route(path = "/article/ArticleContentActivity")
public class ArticleContentActivity extends BaseActivity<ArticleContentPresenter> implements ArticleContentContract.View {
    @Autowired
    public int id;
    @Autowired
    public String url;
    @Autowired
    public String title;
    @Autowired
    public String author;
    @BindView(R.id.webContent)
    FrameLayout mWebContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_content;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_content, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuShare) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_article_url, getString(R.string.app_name), title, url));
            intent.setType("text/plain");
            startActivity(intent);
        } else if (item.getItemId() == R.id.menuLike) {
            if (id == 0) mPresenter.collectOutsideArticle(title, author, url);
            else mPresenter.collectArticle(id);
        } else if (item.getItemId() == R.id.menuBrowser) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(int id, String url, String title, String author) {
        ARouter.getInstance().build("/article/ArticleContentActivity")
                .withInt(Constant.CONTENT_ID_KEY, id)
                .withString(Constant.CONTENT_URL_KEY, url)
                .withString(Constant.CONTENT_TITLE_KEY, title)
                .withString(Constant.CONTENT_AUTHOR_KEY, author)
                .navigation();
    }

    private ChromeClientCallbackManager.ReceivedTitleCallback mReceivedTitleCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            setToolbarTitle(title);
        }
    };
}
