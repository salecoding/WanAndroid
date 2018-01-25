package com.lw.wanandroid.ui.knowledgesystem;

import com.lw.wanandroid.base.BasePresenter;
import com.lw.wanandroid.bean.DataResponse;
import com.lw.wanandroid.bean.KnowledgeSystem;
import com.lw.wanandroid.net.ApiService;
import com.lw.wanandroid.net.RetrofitManager;
import com.lw.wanandroid.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/1/19.
 */

public class KnowledgeSystemPresenter extends BasePresenter<KnowledgeSystemContract.View> implements KnowledgeSystemContract.Presenter {
    @Inject
    public KnowledgeSystemPresenter() {

    }

    @Override
    public void loadKnowledgeSystems() {
        mView.showLoading();
        RetrofitManager.create(ApiService.class)
                .getKnowledgeSystems()
                .compose(RxSchedulers.<DataResponse<List<KnowledgeSystem>>>applySchedulers())
                .compose(mView.<DataResponse<List<KnowledgeSystem>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<KnowledgeSystem>>>() {
                    @Override
                    public void accept(DataResponse<List<KnowledgeSystem>> dataResponse) throws Exception {
                        mView.setKnowledgeSystems(dataResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }

    @Override
    public void refresh() {
        loadKnowledgeSystems();
    }
}
