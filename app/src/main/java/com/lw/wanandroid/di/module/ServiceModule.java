package com.lw.wanandroid.di.module;

import android.app.Service;
import android.content.Context;

import com.lw.wanandroid.di.scope.ContextLife;
import com.lw.wanandroid.di.scope.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lw on 2017/1/19.
 */
@Module
public class ServiceModule {
    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    @ContextLife("Service")
    public Context ProvideServiceContext() {
        return mService;
    }
}
