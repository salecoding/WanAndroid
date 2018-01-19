package com.lw.wanandroid.di.component;

import android.app.Activity;
import android.content.Context;

import com.lw.wanandroid.di.module.ActivityModule;
import com.lw.wanandroid.di.scope.ContextLife;
import com.lw.wanandroid.di.scope.PerActivity;

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

}
