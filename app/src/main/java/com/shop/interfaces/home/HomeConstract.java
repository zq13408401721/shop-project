package com.shop.interfaces.home;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.IndexBean;

public interface HomeConstract {

    interface View extends IBaseView{
        void getHomeDataReturn(IndexBean result);
    }

    interface Persenter extends IBasePersenter<View>{
        void getHomeData();
    }

}
