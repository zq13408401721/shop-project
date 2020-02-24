package com.shop.interfaces.cart;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.RelatedBean;

public interface CartConstart {

    interface View extends IBaseView{
        void getRelatedDataReturn(RelatedBean result);
    }

    interface Persenter extends IBasePersenter<View>{
        void getRelatedData(int id);
    }

}
