package com.lw.wanandroid.di.component;

import android.app.Activity;
import android.content.Context;

import com.lw.wanandroid.di.module.FragmentModule;
import com.lw.wanandroid.di.scope.ContextLife;
import com.lw.wanandroid.di.scope.PerFragment;
import com.lw.wanandroid.ui.home.HomeFragment;
import com.lw.wanandroid.ui.knowledgesystem.KnowledgeSystemFragment;
import com.lw.wanandroid.ui.my.MyFragment;

import dagger.Component;

/**
 * Created by lw on 2017/1/19.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(HomeFragment fragment);

    void inject(KnowledgeSystemFragment fragment);

    void inject(MyFragment fragment);
}
