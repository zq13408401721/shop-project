package com.shop.persenter.cart;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.cart.CartConstart;
import com.shop.models.HttpManager;
import com.shop.models.bean.RelatedBean;
import com.shop.utils.RxUtils;

public class CartPersenter extends BasePersenter<CartConstart.View> implements CartConstart.Persenter {

    //获取商品购买页面的数据
    @Override
    public void getRelatedData(int id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getRelatedData(id)
        .compose(RxUtils.<RelatedBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<RelatedBean>(mView){

            @Override
            public void onNext(RelatedBean relatedBean) {
                mView.getRelatedDataReturn(relatedBean);
            }
        }));
    }

}
