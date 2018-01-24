package com.lw.wanandroid.ui.article;

import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lw.wanandroid.R;
import com.lw.wanandroid.bean.Article;

import javax.inject.Inject;

/**
 * Created by lw on 2018/1/19.
 */

public class ArticleAdapter extends BaseQuickAdapter<Article.DatasBean, BaseViewHolder> {
    private boolean mChapterNameVisible = true;

    @Inject
    public ArticleAdapter() {
        super(R.layout.item_article, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article.DatasBean item) {
        helper.setText(R.id.tvAuthor, item.getAuthor());
        helper.setText(R.id.tvNiceDate, item.getNiceDate());
        helper.setText(R.id.tvTitle, Html.fromHtml(item.getTitle()));
        helper.setText(R.id.tvChapterName, item.getChapterName());
        helper.setImageResource(R.id.ivCollect, item.isCollect() ? R.drawable.ic_action_like : R.drawable.ic_action_no_like);
        helper.addOnClickListener(R.id.tvChapterName);
        helper.addOnClickListener(R.id.ivCollect);
        helper.setVisible(R.id.tvChapterName, mChapterNameVisible);
    }

    public void setChapterNameVisible(boolean chapterNameVisible) {
        this.mChapterNameVisible = chapterNameVisible;
    }
}
