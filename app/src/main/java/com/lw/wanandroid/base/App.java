package com.lw.wanandroid.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.lw.wanandroid.BuildConfig;
import com.lw.wanandroid.constant.Constant;
import com.lw.wanandroid.di.component.ApplicationComponent;
import com.lw.wanandroid.di.component.DaggerApplicationComponent;
import com.lw.wanandroid.di.module.ApplicationModule;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.umeng.commonsdk.UMConfigure;

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
        intARouter();
        FlowManager.init(this);
        /**初始化友盟SDK*/
        UMConfigure.init(this, Constant.UMENG_APP_KEY, Constant.UMENG_CHANNEL, UMConfigure.DEVICE_TYPE_PHONE, null);
    }

    /**
     * 初始化路由
     */
    private void intARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
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
