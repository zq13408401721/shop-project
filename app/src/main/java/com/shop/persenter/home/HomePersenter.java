package com.shop.persenter.home;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.home.HomeConstract;
import com.shop.models.HttpManager;
import com.shop.models.bean.IndexBean;
import com.shop.utils.RxUtils;

public class HomePersenter extends BasePersenter<HomeConstract.View> implements HomeConstract.Persenter {

    //获取主页的具体实现
    @Override
    public void getHomeData() {
        addSubscribe(HttpManager.getInstance().getShopApi().getIndexData()
        .compose(RxUtils.<IndexBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<IndexBean>(mView){

            @Override
            public void onNext(IndexBean indexBean) {
                mView.getHomeDataReturn(indexBean);
            }
        }));
    }

}
