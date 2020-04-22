package com.hotel.interfaces.explore;

import com.hotel.interfaces.IBasePersenter;
import com.hotel.interfaces.IBaseView;
import com.hotel.models.bean.ExploreBean;

import java.util.Map;

public interface ExploreConstract {

    interface View extends IBaseView{
        void getExploreReturn(ExploreBean result);
    }


    interface Persenter extends IBasePersenter<View>{
        void getExplore(Map<String,String> map);
    }

}
