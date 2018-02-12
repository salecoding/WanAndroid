package com.lw.wanandroid.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by lw on 2018/2/2.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "WanAndroid-db";

    public static final int VERSION = 1;
}
