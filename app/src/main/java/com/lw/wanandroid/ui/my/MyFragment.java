package com.lw.wanandroid.ui.my;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lw.wanandroid.R;
import com.lw.wanandroid.base.BaseFragment;
import com.lw.wanandroid.constant.Constant;
import com.lw.wanandroid.event.LoginEvent;
import com.lw.wanandroid.net.CookiesManager;
import com.lw.wanandroid.utils.RxBus;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/1/18.
 */

public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {
    @BindView(R.id.civAvatar)
    CircleImageView mCivAvatar;
    @BindView(R.id.tvNick)
    TextView mTvNick;
    @BindView(R.id.llLogout)
    LinearLayout mLlLogout;
    private boolean mIsLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        setUserStatusInfo();
        /**登陆成功重新设置用户新*/
        RxBus.getInstance().toFlowable(LoginEvent.class).subscribe(new Consumer<LoginEvent>() {
            @Override
            public void accept(LoginEvent event) throws Exception {
                setUserStatusInfo();
            }
        });
    }

    @OnClick({R.id.civAvatar, R.id.tvMyCollection, R.id.tvMyBookmark, R.id.tvSetting, R.id.llLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civAvatar:
                if (!mIsLogin) LoginActivity.start();
                break;
            case R.id.tvMyCollection:
                if (mIsLogin) ARouter.getInstance().build("/my/MyCollectionActivity").navigation();
                else ToastUtils.showShort(R.string.not_login);
                break;
            case R.id.tvMyBookmark:
                if (mIsLogin) ARouter.getInstance().build("/my/MyBookmarkActivity").navigation();
                else ToastUtils.showShort(R.string.not_login);
                break;
            case R.id.tvSetting:
                ARouter.getInstance().build("/setting/SettingActivity").navigation();
                break;
            case R.id.llLogout:
                logout();
                break;
        }
    }

    /**
     * 退出登陆
     */
    private void logout() {
        /**设置退出登陆*/
        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.LOGIN_KEY, false);
        setUserStatusInfo();
        /**清除cookies*/
        CookiesManager.clearAllCookies();
        /**发送退出登陆的消息*/
        RxBus.getInstance().post(new LoginEvent());
    }

    /**
     * 设置用户状态信息
     */
    private void setUserStatusInfo() {
        mIsLogin = SPUtils.getInstance(Constant.SHARED_NAME).getBoolean(Constant.LOGIN_KEY);
        if (mIsLogin) {
            mCivAvatar.setImageResource(R.drawable.ic_head_portrait);
            mTvNick.setText(SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.USERNAME_KEY));
            mLlLogout.setVisibility(View.VISIBLE);
        } else {
            mCivAvatar.setImageResource(R.drawable.ic_avatar);
            mTvNick.setText(R.string.click_avatar_login);
            mLlLogout.setVisibility(View.GONE);
        }
    }

    public static MyFragment newInstance() {
        return new MyFragment();
    }

}
