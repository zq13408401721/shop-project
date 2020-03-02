package com.shop.interfaces.cart;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.CartBean;

public interface ShoppingConstact {

    interface View extends IBaseView{
        void getCartIndexReturn(CartBean result);
    }

    interface Presenter extends IBasePersenter<View>{
        void getCartIndex();
    }

}
