package com.shop.interfaces.sort;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.SortBean;
import com.shop.models.bean.SortGoodsBean;

public interface SortConstract {

    interface View extends IBaseView{

        void getSortReturn(SortBean result);

        void getCurrentSortDataReturn(SortGoodsBean result);

    }

    interface Persenter extends IBasePersenter<View>{

        void getSort();

        void getCurrentSortData(int id);

    }
}
