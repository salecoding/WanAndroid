package com.lw.wanandroid.ui.my;

import android.support.design.widget.TextInputEditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseActivity;
import com.lw.wanandroid.bean.User;
import com.lw.wanandroid.constant.Constant;
import com.lw.wanandroid.event.LoginEvent;
import com.lw.wanandroid.utils.RxBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lw on 2018/1/24.
 */
@Route(path = "/my/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.etUsername)
    TextInputEditText mEtUsername;
    @BindView(R.id.etPassword)
    TextInputEditText mEtPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        mEtUsername.setText(SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.USERNAME_KEY));
        mEtPassword.setText(SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.PASSWORD_KEY));
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            ToastUtils.showShort(R.string.the_username_or_password_can_not_be_empty);
            return;
        }
        mPresenter.login(username, password);
    }

    @Override
    public void loginSuccess(User user) {
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.LOGIN_KEY, true);
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.USERNAME_KEY, user.getUsername());
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.PASSWORD_KEY, user.getPassword());
        /**登陆成功通知其他界面刷新*/
        RxBus.getInstance().post(new LoginEvent());
        this.finish();
    }

    public static void start() {
        ARouter.getInstance().build("/my/LoginActivity").navigation();
    }

}
