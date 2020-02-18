package com.shop.persenter.home;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.home.BrandConstract;
import com.shop.models.HttpManager;
import com.shop.models.bean.BrandBean;
import com.shop.models.bean.BrandGoodsBean;
import com.shop.utils.RxUtils;

public class BrandPersenter extends BasePersenter<BrandConstract.View> implements BrandConstract.Persenter {


    @Override
    public void getBrandInfo(String id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getBrandInfo(id)
        .compose(RxUtils.<BrandBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<BrandBean>(mView){

            @Override
            public void onNext(BrandBean brandBean) {
                mView.getBrandInfoReturn(brandBean);
            }
        }));
    }

    @Override
    public void getBrandGoods(String brandId, int page, int size) {
        addSubscribe(HttpManager.getInstance().getShopApi().getBrandGoods(brandId,page,size)
                .compose(RxUtils.<BrandGoodsBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandGoodsBean>(mView){

                    @Override
                    public void onNext(BrandGoodsBean brandGoodsBean) {
                        mView.getBrandGoodsReturn(brandGoodsBean);
                    }
                }));
    }
}
