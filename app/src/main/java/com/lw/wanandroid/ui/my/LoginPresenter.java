package com.lw.wanandroid.ui.my;

import com.lw.wanandroid.base.BasePresenter;
import com.lw.wanandroid.bean.DataResponse;
import com.lw.wanandroid.bean.User;
import com.lw.wanandroid.net.ApiService;
import com.lw.wanandroid.net.RetrofitManager;
import com.lw.wanandroid.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/1/24.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(String username, String password) {
        RetrofitManager.create(ApiService.class)
                .login(username, password)
                .compose(RxSchedulers.<DataResponse<User>>applySchedulers())
                .compose(mView.<DataResponse<User>>bindToLife())
                .subscribe(new Consumer<DataResponse<User>>() {
                    @Override
                    public void accept(DataResponse<User> response) throws Exception {
                        if (response.getErrorCode() == 0) {
                            mView.loginSuccess(response.getData());
                        } else {
                            mView.showFaild(String.valueOf(response.getErrorMsg()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }
}
