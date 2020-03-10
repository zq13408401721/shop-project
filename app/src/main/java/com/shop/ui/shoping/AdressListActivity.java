package com.shop.ui.shoping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.R;
import com.shop.base.BaseActivity;
import com.shop.base.BaseAdapter;
import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.cart.ShoppingConstact;
import com.shop.models.bean.AddressBean;
import com.shop.persenter.cart.AdressPresenter;
import com.shop.ui.shoping.adapters.AdressAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdressListActivity extends BaseActivity<ShoppingConstact.AdressPresenter> implements ShoppingConstact.AdressView,
        BaseAdapter.ItemClickHandler {
    @BindView(R.id.recy_adress_list)
    RecyclerView recyAdressList;
    @BindView(R.id.txt_new)
    TextView txtNew;

    AdressAdapter adressAdapter;
    List<AddressBean.DataBean> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_adress_list;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adressAdapter = new AdressAdapter(list,this);
        recyAdressList.setLayoutManager(new LinearLayoutManager(this));
        recyAdressList.setAdapter(adressAdapter);

        //设置一个条目中组件点击事件的响应方法
        adressAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.img_editor:  //编辑地址的页面
                        AddressBean.DataBean item = (AddressBean.DataBean) v.getTag();
                        //进入地址编辑的页面
                        Intent intent = new Intent(context,AdressEditorActivity.class);
                        intent.putExtra("adress",item);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        persenter.getAdressList();
    }

    @Override
    protected ShoppingConstact.AdressPresenter createPersenter() {
        return new AdressPresenter();
    }

    //调整新建地址页面
    @OnClick(R.id.txt_new)
    public void onViewClicked() {
        Intent intent = new Intent(this,AdressEditorActivity.class);
        startActivity(intent);
    }

    //数据获取返回
    @Override
    public void getAdressListReturn(AddressBean result) {
        adressAdapter.updata(result.getData());
    }

    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        AddressBean.DataBean item = list.get(position);
        //进入地址编辑的页面
        Intent intent = new Intent(this,AdressEditorActivity.class);
        intent.putExtra("adress",item);
        startActivity(intent);
    }
}
