package com.hotel.persenter.explore;

import com.hotel.base.BasePersenter;
import com.hotel.common.CommonSubscriber;
import com.hotel.interfaces.explore.ExploreConstract;
import com.hotel.models.HttpManager;
import com.hotel.models.bean.ExploreBean;
import com.hotel.utils.RxUtils;

import java.util.Map;

public class ExplorePersenter extends BasePersenter<ExploreConstract.View> implements ExploreConstract.Persenter {
    @Override
    public void getExplore(Map<String, String> map) {
        addSubscribe(HttpManager.getInstance().getServerApi().getExplore(map)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<ExploreBean>(mView){

            @Override
            public void onNext(ExploreBean exploreBean) {
                mView.getExploreReturn(exploreBean);
            }
        }));
    }
}
