package com.shop.persenter.test;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.test.TestConstract;
import com.shop.models.HttpManager;
import com.shop.models.bean.ChaptersBean;
import com.shop.utils.RxUtils;

import io.reactivex.subscribers.ResourceSubscriber;

public class TestPersenter extends BasePersenter<TestConstract.View> implements TestConstract.Persenter {

    @Override
    public void getChapters() {
        addSubscribe(HttpManager.getInstance().getWanApi().getChapters()
        .compose(RxUtils.<ChaptersBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<ChaptersBean>(mView){

            @Override
            public void onNext(ChaptersBean chaptersBean) {
                mView.getChaptersReturn(chaptersBean);
            }
        }));

        //网络请求不用背压式
        /*HttpManager.getInstance().getWanApi().getChapters()
                .compose(RxUtils.<ChaptersBean>rxScheduler())
                .subscribeWith(new ResourceSubscriber<ChaptersBean>() {
                    @Override
                    public void onNext(ChaptersBean chaptersBean) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }
}
