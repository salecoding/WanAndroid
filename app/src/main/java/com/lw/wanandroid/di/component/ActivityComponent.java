package com.lw.wanandroid.di.component;

import android.app.Activity;
import android.content.Context;

import com.lw.wanandroid.di.module.ActivityModule;
import com.lw.wanandroid.di.scope.ContextLife;
import com.lw.wanandroid.di.scope.PerActivity;
import com.lw.wanandroid.ui.article.ArticleContentActivity;
import com.lw.wanandroid.ui.hotsearch.SearchActivity;
import com.lw.wanandroid.ui.my.LoginActivity;

import dagger.Component;

/**
 * Created by lw on 2017/1/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(SearchActivity activity);

    void inject(LoginActivity activity);

    void inject(ArticleContentActivity activity);
}
