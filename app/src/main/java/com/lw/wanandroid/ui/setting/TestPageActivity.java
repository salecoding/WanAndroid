package com.lw.wanandroid.ui.setting;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lw on 2018/2/12.
 */
@Route(path = "/setting/TestPageActivity")
public class TestPageActivity extends BaseActivity {
    private static final String TAG = "TestPageActivity";
    @BindView(R.id.btnTest1)
    Button mBtnTest1;
    @BindView(R.id.btnTest2)
    Button mBtnTest2;
    @BindView(R.id.btnTest3)
    Button mBtnTest3;
    OkHttpClient mOkHttpClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_page;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @OnClick({R.id.btnTest1, R.id.btnTest2, R.id.btnTest3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnTest1:
                test1();
                break;
            case R.id.btnTest2:
                break;
            case R.id.btnTest3:
                break;
        }
    }

    private void test1() {
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        Call call = mOkHttpClient.newCall(request);
        try {
            //Response response = call.execute();
            //Log.d(TAG, "test1: " + response.body().string());
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "test1: " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d(TAG, "test1: " + response.body().string());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
