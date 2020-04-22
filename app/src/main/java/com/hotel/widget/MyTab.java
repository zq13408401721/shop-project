package com.hotel.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.hotel.R;

import org.xml.sax.Attributes;

/**
 * 自定义view实现tab切换效果
 */
public class MyTab extends ConstraintLayout implements View.OnClickListener {

    TextView tabLt; //左边文本
    TextView tabRt; //右边文本
    Context context;
    String tabTitle1;
    String tabTitle2;

    private TabClick tabClick; //点击tab接口回调




    public MyTab(Context context) {
        super(context);
        this.context = context;
    }

    public MyTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyTab);
        tabTitle1 = typedArray.getString(R.styleable.MyTab_tab_lt_title);
        tabTitle2 = typedArray.getString(R.styleable.MyTab_tab_rt_title);
        typedArray.recycle();
    }

    public MyTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyTab);
        tabTitle1 = typedArray.getString(R.styleable.MyTab_tab_lt_title);
        tabTitle2 = typedArray.getString(R.styleable.MyTab_tab_rt_title);
        typedArray.recycle();
    }

    /**
     * 初始化自定义View暂时有外部触发
     */
    public void initVeiw(){
        tabLt = findViewById(R.id.txt_left);
        tabRt = findViewById(R.id.txt_right);
        tabLt.setOnClickListener(this);
        tabRt.setOnClickListener(this);
        //自定义属性初始化值
        if(!TextUtils.isEmpty(tabTitle1)){
            tabLt.setText(tabTitle1);
        }
        if(!TextUtils.isEmpty(tabTitle2)){
            tabRt.setText(tabTitle2);
        }
    }

    /**
     * 动态设置tabtitle
     * @param left
     * @param right
     */
    public void addTabTitle(String left,String right){
        if(tabLt != null && !TextUtils.isEmpty(left)) tabLt.setText(left);
        if(tabRt != null && !TextUtils.isEmpty(right)) tabRt.setText(right);
    }

    /**
     * 设置tab点击的回调接口
     * @param tabClick
     */
    public void addTabClickListener(TabClick tabClick){
        this.tabClick = tabClick;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_left:
                tabClickEvt(0);
                //左边选中
                resetTabStyle();
                tabLt.setBackgroundResource(R.drawable.shape_tablt_select);
                break;
            case R.id.txt_right:
                tabClickEvt(1);
                //右边选中
                resetTabStyle();
                tabRt.setBackgroundResource(R.drawable.shape_tabrt_select);
                break;
        }
    }

    /**
     * 重置tab选中样式
     */
    private void resetTabStyle(){
        tabLt.setBackgroundResource(R.drawable.shape_tablt_normal);
        tabRt.setBackgroundResource(R.drawable.shape_tabrt_normal);
    }

    private void tabClickEvt(int pos){
        if(this.tabClick != null){
            this.tabClick.tabClickFun(pos);
        }
    }

    /**
     * 回调接口
     */
    public interface TabClick{
        void tabClickFun(int pos);
    }
}
