package com.shop.persenter.sort;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.sort.SortConstract;
import com.shop.models.HttpManager;
import com.shop.models.bean.SortBean;
import com.shop.models.bean.SortGoodsBean;
import com.shop.utils.RxUtils;

public class SortPersenter extends BasePersenter<SortConstract.View> implements SortConstract.Persenter {

    @Override
    public void getSort() {
        addSubscribe(HttpManager.getInstance().getShopApi().getSort()
        .compose(RxUtils.<SortBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<SortBean>(mView) {
            @Override
            public void onNext(SortBean sortBean) {
                mView.getSortReturn(sortBean);
            }
        }));
    }

    @Override
    public void getCurrentSortData(int id) {
        addSubscribe(HttpManager.getInstance().getShopApi().getCurrentSortData(id)
                .compose(RxUtils.<SortGoodsBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<SortGoodsBean>(mView) {
                    @Override
                    public void onNext(SortGoodsBean sortGoodsBean) {
                        mView.getCurrentSortDataReturn(sortGoodsBean);
                    }
                }));
    }
}
