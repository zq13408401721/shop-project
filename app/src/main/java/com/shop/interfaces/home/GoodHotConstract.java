package com.shop.interfaces.home;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.GoodHotBean;

import java.util.Map;

/**
 * 人气推荐
 */
public interface GoodHotConstract {

    interface View extends IBaseView{
        void getGoodHotReturn(GoodHotBean result);
    }

    interface Persenter extends IBasePersenter<View>{
        void getGoodHot(Map<String,String> map);
    }

}
