package com.lw.wanandroid.base;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.lw.wanandroid.di.component.ApplicationComponent;
import com.lw.wanandroid.di.component.DaggerApplicationComponent;
import com.lw.wanandroid.di.module.ApplicationModule;

/**
 * Created by lw on 2018/1/18.
 */

public class App extends Application {
    private ApplicationComponent mApplicationComponent;
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initApplicationComponent();
        Utils.init(this);
    }

    /**
     * 初始化ApplicationComponent
     */
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static App getInstance() {
        return mInstance;
    }
}
