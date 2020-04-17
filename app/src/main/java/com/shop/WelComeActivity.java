package com.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shop.common.Constant;
import com.shop.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页的实现
 */
public class WelComeActivity extends AppCompatActivity implements View.OnClickListener {


    ViewPager viewPager;
    LinearLayout layoutDots; //dots

    List<View> list;
    int[] res = {R.mipmap.welcome_1,R.mipmap.welcome_2,R.mipmap.welcome_3};
    ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        initView();
    }

    private void initView(){
        viewPager = findViewById(R.id.viewpager);
        layoutDots = findViewById(R.id.layout_dots);

        //初始化提示的点
        dots = new ImageView[res.length];
        //初始化viewpager的内容
        list = new ArrayList<>();
        for(int i=0; i<res.length; i++){
            View view = LayoutInflater.from(this).inflate(R.layout.layout_welcome_page,null);
            list.add(view);
            ImageView img = view.findViewById(R.id.img_page);
            img.setImageResource(res[i]);
            if(i == res.length -1){
                Button btn = view.findViewById(R.id.btn_comeIn);
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(this);
            }
            //初始化dots的图片和样式可
            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.shape_dots_normal);
            layoutDots.addView(dots[i]);
            ViewGroup.LayoutParams param = dots[i].getLayoutParams();
            ViewGroup.MarginLayoutParams marginParam = null;
            if(param instanceof ViewGroup.MarginLayoutParams){
                marginParam = (ViewGroup.MarginLayoutParams) param;
            }else{
                marginParam = new ViewGroup.MarginLayoutParams(param);
            }
            marginParam.leftMargin = 20;
            dots[i].setLayoutParams(marginParam);
        }

        viewPager.setAdapter(new VGAdapter());
        dots[0].setImageResource(R.drawable.shape_dots_select);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position < dots.length){
                    resetDots();
                    dots[position].setImageResource(R.drawable.shape_dots_select);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //还原dots为默认效果
    private void resetDots(){
        for(int i=0; i<dots.length; i++){
            dots[i].setImageResource(R.drawable.shape_dots_normal);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_comeIn:
                gotoMain();
                break;
        }
    }

    //从引导页进入主页
    private void gotoMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        //引导页查看完毕以后把记录设置为已查看
        SpUtils.getInstance().setValue(Constant.WELCOME_READ,true);
        finish();
    }


    class VGAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(list.get(position));
        }
    }
}
