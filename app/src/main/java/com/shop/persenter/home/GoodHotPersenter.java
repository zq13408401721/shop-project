package com.shop.persenter.home;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.home.GoodHotConstract;
import com.shop.models.HttpManager;
import com.shop.models.bean.BrandBean;
import com.shop.models.bean.GoodHotBean;
import com.shop.utils.RxUtils;

import java.util.Map;

public class GoodHotPersenter extends BasePersenter<GoodHotConstract.View> implements GoodHotConstract.Persenter {
    @Override
    public void getGoodHot(Map<String, String> map) {
        addSubscribe(HttpManager.getInstance().getShopApi().getGoodHot(map)
                .compose(RxUtils.<GoodHotBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodHotBean>(mView){

                    @Override
                    public void onNext(GoodHotBean result) {
                        mView.getGoodHotReturn(result);
                    }
                }));
    }
}
