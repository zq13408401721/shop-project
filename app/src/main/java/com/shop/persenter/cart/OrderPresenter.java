package com.shop.persenter.cart;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.cart.ShoppingConstact;
import com.shop.models.HttpManager;
import com.shop.models.bean.OrderInfoBean;
import com.shop.utils.RxUtils;

public class OrderPresenter extends BasePersenter<ShoppingConstact.OrderView> implements ShoppingConstact.OrderPresenter {

    //获取订单信息
    @Override
    public void getOrderList(int addressId, int couponId) {
        addSubscribe(HttpManager.getInstance().getShopApi().getOrderInfo(addressId,couponId)
        .compose(RxUtils.<OrderInfoBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<OrderInfoBean>(mView){

            @Override
            public void onNext(OrderInfoBean orderInfoBean) {
                mView.getOrderListReturn(orderInfoBean);
            }
        }));
    }
}
