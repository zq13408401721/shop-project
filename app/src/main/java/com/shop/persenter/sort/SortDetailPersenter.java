package com.shop.persenter.sort;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.sort.SortConstract;
import com.shop.models.HttpManager;
import com.shop.models.bean.SortDetailGoodsBean;
import com.shop.models.bean.SortDetailTabBean;
import com.shop.utils.RxUtils;

public class SortDetailPersenter extends BasePersenter<SortConstract.DetailView> implements SortConstract.DetailPersenter {

    @Override
    public void getSortDetailTab(int id) {
       addSubscribe(HttpManager.getInstance().getShopApi().getSortDetailTab(id)
       .compose(RxUtils.<SortDetailTabBean>rxScheduler())
       .subscribeWith(new CommonSubscriber<SortDetailTabBean>(mView){
           @Override
           public void onNext(SortDetailTabBean sortDetailTabBean) {
               mView.getSortDetailTabReturn(sortDetailTabBean);
           }
       }));
    }

    @Override
    public void getSortDetailGoods(int id, int page, int size) {
        addSubscribe(HttpManager.getInstance().getShopApi().getSortDetailGoods(id,page,size)
                .compose(RxUtils.<SortDetailGoodsBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<SortDetailGoodsBean>(mView){
                    @Override
                    public void onNext(SortDetailGoodsBean sortDetailGoodsBean) {
                        mView.getSortDetailGoodsReturn(sortDetailGoodsBean);
                    }
                }));
    }

}
