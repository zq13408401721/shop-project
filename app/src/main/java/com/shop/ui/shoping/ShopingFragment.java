package com.shop.ui.shoping;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.R;
import com.shop.base.BaseAdapter;
import com.shop.base.BaseFragment;
import com.shop.interfaces.cart.ShoppingConstact;
import com.shop.models.bean.CartBean;
import com.shop.persenter.cart.ShoppingPresenter;
import com.shop.ui.login.LoginActivity;
import com.shop.ui.shoping.adapters.ShoppingAdapter;
import com.shop.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopingFragment extends BaseFragment<ShoppingConstact.Presenter> implements ShoppingConstact.View,
        BaseAdapter.ItemClickHandler {
    @BindView(R.id.radio_all)
    RadioButton radioAll;
    @BindView(R.id.txt_TotalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.txt_order)
    TextView txtOrder;
    @BindView(R.id.txt_edit)
    TextView txtEdit;
    @BindView(R.id.layout_common)
    ConstraintLayout layoutCommon;
    @BindView(R.id.cart_list)
    RecyclerView cartList;

    ShoppingAdapter shoppingAdapter;
    List<CartBean.DataBean.CartListBean> list;
    @Override
    protected int getLayout() {
        return R.layout.fragment_shoping;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        shoppingAdapter = new ShoppingAdapter(list,context);
        cartList.setLayoutManager(new LinearLayoutManager(context));
        cartList.setAdapter(shoppingAdapter);
        radioAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSelectAll();
                shoppingAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initData() {

        //如果用户没有登录就要
        String token = SpUtils.getInstance().getString("token");
        if (TextUtils.isEmpty(token)) {
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        } else {
            persenter.getCartIndex();
        }
    }

    /*@Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if(requestCode == 100){
            if(persenter != null) persenter.getCartIndex();
        }
    }*/

    @Override
    protected ShoppingConstact.Presenter createPersenter() {
        return new ShoppingPresenter();
    }

    @Override
    public void getCartIndexReturn(CartBean result) {
        shoppingAdapter.updata(result.getData().getCartList());
    }

    @OnClick({R.id.txt_order,R.id.txt_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_order:

                break;
            case R.id.txt_edit:
                String str = txtEdit.getText().toString();
                if(str.equals("编辑")){
                    txtEdit.setText("完成");
                    shoppingAdapter.isEdit = true;
                    shoppingAdapter.notifyDataSetChanged();
                }else{
                    txtEdit.setText("编辑");
                    shoppingAdapter.isEdit = false;
                    //提交编辑页面的数据
                }
                break;
        }
    }

    //radio状态变化
    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        updateSelectAll();
    }

    /**
     * 设置全部选中
     */
    private void setSelectAll(){
        for(CartBean.DataBean.CartListBean item:list){
            item.isSelect = true;
        }
    }

    /**
     * 检查是否全部选择
     * @return
     */
    private void updateSelectAll(){
        //int[] arr = new int[3]; //用一个数组来记录当前是否全选，选中的商品数量，总的价格
        int totalPrice = 0;
        int nums = 0;
        boolean isAll = true; //是否全选
        for(int i=0; i<list.size(); i++){
            if(!shoppingAdapter.isEdit){
                boolean isSelect = list.get(i).isSelect;
                //记录判断是否全选
                if(isAll){
                    isAll = isSelect;
                }
                //计算选中的商品数量和价格
                if(isSelect){
                    nums += list.get(i).getNumber();
                    totalPrice += list.get(i).getNumber()*list.get(i).getRetail_price();
                }
            }else{
                boolean isDelSelect = list.get(i).isDelSelect;
                if(isAll){
                    isAll = isDelSelect;
                }
                if(isDelSelect){
                    nums += list.get(i).getNumber();
                }
            }
        }

        radioAll.setChecked(isAll);
        radioAll.setText("全选("+nums+")");
        if(!shoppingAdapter.isEdit){
            String price = context.getResources().getString(R.string.price_news_model);
            price = price.replace("$",String.valueOf(totalPrice));
            txtTotalPrice.setText(price);
        }else{
            txtTotalPrice.setText("");
        }
    }
}
