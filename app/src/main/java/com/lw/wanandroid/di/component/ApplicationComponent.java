package com.lw.wanandroid.di.component;

import android.content.Context;

import com.lw.wanandroid.di.module.ApplicationModule;
import com.lw.wanandroid.di.scope.ContextLife;
import com.lw.wanandroid.di.scope.PerApp;

import dagger.Component;


/**
 * Created by lw on 2017/1/19.
 */
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}