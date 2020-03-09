package com.shop.persenter.cart;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.cart.ShoppingConstact;
import com.shop.models.HttpManager;
import com.shop.models.bean.AddressBean;
import com.shop.utils.RxUtils;

public class AdressPresenter extends BasePersenter<ShoppingConstact.AdressView> implements ShoppingConstact.AdressPresenter {

    @Override
    public void getAdressList() {
        addSubscribe(HttpManager.getInstance().getShopApi().getAddress()
        .compose(RxUtils.<AddressBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<AddressBean>(mView){

            @Override
            public void onNext(AddressBean addressBean) {
                mView.getAdressListReturn(addressBean);
            }
        }));
    }
}
