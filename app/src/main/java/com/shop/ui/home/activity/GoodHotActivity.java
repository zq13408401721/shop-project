package com.shop.ui.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.R;
import com.shop.base.BaseActivity;
import com.shop.interfaces.home.GoodHotConstract;
import com.shop.models.bean.GoodHotBean;
import com.shop.persenter.home.GoodHotPersenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodHotActivity extends BaseActivity<GoodHotConstract.Persenter> implements GoodHotConstract.View {
    @BindView(R.id.img_top)
    ImageView imgTop;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.layout_top)
    ConstraintLayout layoutTop;
    @BindView(R.id.txt_all)
    TextView txtAll;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_arrow_up)
    TextView txtArrowUp;
    @BindView(R.id.txt_arrow_down)
    TextView txtArrowDown;
    @BindView(R.id.layout_price)
    ConstraintLayout layoutPrice;
    @BindView(R.id.txt_type)
    TextView txtType;
    @BindView(R.id.layout_bar)
    ConstraintLayout layoutBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    int isHot = 1;
    int page = 1;
    int pageSize = 1000;
    String order = "desc";
    String sort = "default"; // default || price || category
    int categoryId = 0;

    //popwindow自定义实现
    PopupWindow popupWindow;

    GoodHotBean goodHotBean;

    @Override
    protected int getLayout() {
        return R.layout.activity_goodhot;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getData();
    }


    @Override
    protected GoodHotConstract.Persenter createPersenter() {
        return new GoodHotPersenter();
    }

    @Override
    public void getGoodHotReturn(GoodHotBean result) {
        goodHotBean = result;
    }
    @OnClick({R.id.txt_all, R.id.txt_price, R.id.txt_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_all:
                closePopWindow();
                sort = "default";
                resetTxt();
                txtAll.setTextColor(Color.parseColor("#ff0000"));
                break;
            case R.id.txt_price:
                closePopWindow();
                sort = "price";
                resetTxt();
                txtPrice.setTextColor(Color.parseColor("#ff0000"));
                if(order.equals("asc")){
                    order = "desc";
                    txtArrowDown.setTextColor(Color.parseColor("#ff0000"));
                }else {
                    order = "asc";
                    txtArrowUp.setTextColor(Color.parseColor("#ff0000"));
                }
                getData();
                break;
            case R.id.txt_type:
                sort = "category";
                //弹出条件框
                openCategoryWindow();
                resetTxt();
                txtType.setTextColor(Color.parseColor("#ff0000"));
                break;
        }
    }

    /**
     * 打开popwindow
     */
    private void openCategoryWindow(){
        if(popupWindow == null){
            LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_horizontal_popwin,null);
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAsDropDown(layoutBar);
            //通过数据动态填充
            if(goodHotBean.getData().getFilterCategory().size() > 0){
                for(GoodHotBean.DataBeanX.FilterCategoryBean item:goodHotBean.getData().getFilterCategory()){
                    View itemView = LayoutInflater.from(this).inflate(R.layout.txt_filter,null);
                    TextView txtItem = itemView.findViewById(R.id.txt_item);
                    itemView.setTag(item.getId());
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            resetHorizontal(view);
                            txtItem.setTextColor(Color.parseColor("#ff0000"));
                            categoryId = (int) v.getTag();
                            getData();
                        }
                    });
                    txtItem.setText(item.getName());
                    view.addView(itemView);
                }
            }
        }
    }

    //重置横向布局容器中的组件样式
    private void resetHorizontal(LinearLayout layout){
        if(layout.getChildCount() > 0){
            for(int i=0; i<layout.getChildCount(); i++){
                if(layout.getChildAt(i) instanceof TextView){
                    TextView child = (TextView) layout.getChildAt(i);
                    child.setTextColor(Color.parseColor("#000000"));
                }
            }
        }
    }

    private void closePopWindow(){
        if(popupWindow != null && popupWindow.isShowing()){
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    /**
     * 对筛选条件的文本样式进行重置
     */
    private void resetTxt(){
        txtAll.setTextColor(Color.parseColor("#000000"));
        txtPrice.setTextColor(Color.parseColor("#000000"));
        txtType.setTextColor(Color.parseColor("#000000"));
        txtArrowUp.setTextColor(Color.parseColor("#000000"));
        txtArrowDown.setTextColor(Color.parseColor("#000000"));
    }

    /**
     * 请求对应的数据
     */
    private void getData(){
        Map<String,String> map = new HashMap();
        map.put("isHot",String.valueOf(isHot));
        map.put("page",String.valueOf(page));
        map.put("pageSize",String.valueOf(pageSize));
        map.put("order",order);
        map.put("sort",sort);
        map.put("categoryId",String.valueOf(categoryId));
        persenter.getGoodHot(map);
    }
}
