package com.shop.persenter.cart;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.cart.ShoppingConstact;
import com.shop.models.HttpManager;
import com.shop.models.bean.CartBean;
import com.shop.utils.RxUtils;


public class ShoppingPresenter extends BasePersenter<ShoppingConstact.View> implements ShoppingConstact.Presenter {
    @Override
    public void getCartIndex() {
        addSubscribe(HttpManager.getInstance().getShopApi().getCartIndex()
        .compose(RxUtils.<CartBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<CartBean>(mView) {
            @Override
            public void onNext(CartBean cartBean) {
                mView.getCartIndexReturn(cartBean);
            }
        }));
    }
}
