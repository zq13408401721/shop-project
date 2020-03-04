package com.shop.persenter.cart;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.cart.ShoppingConstact;
import com.shop.models.HttpManager;
import com.shop.models.bean.CartBean;
import com.shop.models.bean.CartGoodsCheckBean;
import com.shop.models.bean.CartGoodsDeleteBean;
import com.shop.models.bean.CartGoodsUpdateBean;
import com.shop.utils.RxUtils;

import io.reactivex.functions.Function;


public class ShoppingPresenter extends BasePersenter<ShoppingConstact.View> implements ShoppingConstact.Presenter {
    @Override
    public void getCartIndex() {
        addSubscribe(HttpManager.getInstance().getShopApi().getCartIndex()
        .compose(RxUtils.<CartBean>rxScheduler())
        .map(new Function<CartBean, CartBean>() {
            @Override
            public CartBean apply(CartBean cartBean) throws Exception {
                for(CartBean.DataBean.CartListBean item:cartBean.getData().getCartList()){
                    item.isSelect = item.getChecked() == 0 ? true : false;
                }
                return cartBean;
            }
        })
        .subscribeWith(new CommonSubscriber<CartBean>(mView) {
            @Override
            public void onNext(CartBean cartBean) {
                mView.getCartIndexReturn(cartBean);
            }
        }));
    }

    //设置商品选中状态 pids 商品ID isChecked是否选中 0选中 1非选中
    @Override
    public void setCartGoodsChecked(String pids, int isChecked) {
        addSubscribe(HttpManager.getInstance().getShopApi().setCartGoodsCheck(pids,isChecked)
                .compose(RxUtils.<CartGoodsCheckBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartGoodsCheckBean>(mView) {
                    @Override
                    public void onNext(CartGoodsCheckBean cartBean) {
                        mView.setCartGoodsCheckedReturn(cartBean);
                    }
                }));
    }

    //更新商品列表信息
    @Override
    public void updateCartGoods(String pids, String goodsId, int number, int id) {
        addSubscribe(HttpManager.getInstance().getShopApi().updateCartGoods(pids,goodsId,number,id)
                .compose(RxUtils.<CartGoodsUpdateBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartGoodsUpdateBean>(mView) {
                    @Override
                    public void onNext(CartGoodsUpdateBean updateBean) {
                        mView.updateCartGoodsReturn(updateBean);
                    }
                }));
    }

    //删除商品
    @Override
    public void deleteCartGoods(String pids) {
        addSubscribe(HttpManager.getInstance().getShopApi().deleteCartGoods(pids)
                .compose(RxUtils.<CartGoodsDeleteBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartGoodsDeleteBean>(mView) {
                    @Override
                    public void onNext(CartGoodsDeleteBean deleteBean) {
                        mView.deleteCartGoodsReturn(deleteBean);
                    }
                }));
    }
}
