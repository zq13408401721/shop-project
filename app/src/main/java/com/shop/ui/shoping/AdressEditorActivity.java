package com.shop.ui.shoping;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.shop.R;
import com.shop.base.BaseActivity;
import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.cart.ShoppingConstact;
import com.shop.models.bean.AddressBean;
import com.shop.models.bean.AdressSaveBean;
import com.shop.models.bean.RegionBean;
import com.shop.persenter.cart.AdressNewPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://blog.csdn.net/ZkMao1007/article/details/80109342
 * popupwindow用法
 */
public class AdressEditorActivity extends BaseActivity<ShoppingConstact.AdressNewPresenter> implements ShoppingConstact.AdressNewView {
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.txt_adress)
    TextView txtAdress;
    @BindView(R.id.txt_adress_info)
    TextView txtAdressInfo;
    @BindView(R.id.checkbox_default)
    CheckBox checkboxDefault;
    @BindView(R.id.txt_cancel)
    TextView txtCancel;
    @BindView(R.id.txt_save)
    TextView txtSave;

    PopupWindow popupWindow;//
    AddressBean.DataBean addressBean;

    @Override
    protected int getLayout() {
        return R.layout.activity_adress_editor;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (getIntent().hasExtra("adress")) {
            addressBean = (AddressBean.DataBean) getIntent().getSerializableExtra("adress");
        }
    }

    @Override
    protected ShoppingConstact.AdressNewPresenter createPersenter() {
        return new AdressNewPresenter();
    }

    @OnClick({R.id.txt_cancel, R.id.txt_save,R.id.txt_adress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_cancel:
                finish();
                break;
            case R.id.txt_save:
                saveAdress();
                break;
            case R.id.txt_adress:
                openAdressWindow();
                break;
        }
    }

    //打开popupwindow
    private void openAdressWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow_adress,null);
        popupWindow = new PopupWindow(view,200, ViewGroup.LayoutParams.MATCH_PARENT);

    }


    //执行保存地址操作
    private void saveAdress(){
        if(addressBean == null){
            addressBean = new AddressBean.DataBean();
        }
        //persenter.saveAdress();
    }

    //保存地址返回
    @Override
    public void saveAdressReturn(AdressSaveBean result) {

    }

    //获取省市区数据返回
    @Override
    public void getRegionReturn(RegionBean result) {

    }
}
