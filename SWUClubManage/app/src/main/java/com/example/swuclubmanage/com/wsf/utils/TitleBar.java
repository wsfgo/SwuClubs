package com.example.swuclubmanage.com.wsf.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swuclubmanage.R;

/**
 * Created by 王书发 on 2016/6/8.
 */
public class TitleBar {
    private View rl_titlebar;
    private ImageView leftIv=null;
    private TextView leftTv;
    private ImageView rightIv;
    private TextView rightTv;

    private TextView titleTv;

    public TitleBar(Activity context){
        rl_titlebar=context.findViewById(R.id.rl_title_bar1);
        //rl_titlebar= LayoutInflater.from(context).inflate(R.layout.include_titlebar,null);
        leftIv= (ImageView) rl_titlebar.findViewById(R.id.titlebar_left_iv1);
        leftTv= (TextView) rl_titlebar.findViewById(R.id.titlebar_left_tv1);
        rightIv= (ImageView) rl_titlebar.findViewById(R.id.titlebar_right_iv1);
        rightTv= (TextView) rl_titlebar.findViewById(R.id.titlebar_right_tv1);
        titleTv= (TextView) rl_titlebar.findViewById(R.id.titlebar_title1);
    }

    public TitleBar(View context){
        rl_titlebar=context.findViewById(R.id.rl_title_bar1);
        leftIv= (ImageView) rl_titlebar.findViewById(R.id.titlebar_left_iv1);
        leftTv= (TextView) rl_titlebar.findViewById(R.id.titlebar_left_tv1);
        rightIv= (ImageView) rl_titlebar.findViewById(R.id.titlebar_right_iv1);
        rightTv= (TextView) rl_titlebar.findViewById(R.id.titlebar_right_tv1);
        titleTv= (TextView) rl_titlebar.findViewById(R.id.titlebar_title1);
    }

    /**
     * 设置标题
     * @param title
     * @return
     */
    public TitleBar setTitle(String title){
        titleTv.setVisibility(TextUtils.isEmpty(title)?View.GONE:View.VISIBLE);
        titleTv.setText(title);
        return this;
    }

    /**
     * 设置标题栏的背景颜色
     */
    public TitleBar setBackgroundColor(int colorId){
        rl_titlebar.setBackgroundColor(colorId);
        return this;
    }

    /**
     * 设置标题栏左边的图片
     */
    public TitleBar setLeftImage(int resId){
        leftIv.setVisibility(resId>0?View.VISIBLE:View.GONE);
        leftIv.setImageResource(resId);
        return  this;
    }

    /**
     * 设置左边的文字
     */
    public TitleBar setLeftTitle(String title){
        leftTv.setVisibility(TextUtils.isEmpty(title)?View.GONE:View.VISIBLE);
        leftTv.setText(title);
        return this;
    }

    /**
     * 设置左边的点击事件
     */
    public TitleBar setLeftOnClickListener(View.OnClickListener listener){
        if(leftIv.getVisibility()==View.VISIBLE){
            leftIv.setOnClickListener(listener);
        }else if(leftTv.getVisibility()==View.VISIBLE){
            leftTv.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置标题栏右边的图片
     */
    public TitleBar setRightImage(int resId){
        rightIv.setVisibility(resId>0?View.VISIBLE:View.GONE);
        rightIv.setImageResource(resId);
        return  this;
    }

    /**
     * 设置右边的文字
     */
    public TitleBar setRightTitle(String title){
        rightTv.setVisibility(TextUtils.isEmpty(title)?View.GONE:View.VISIBLE);
        rightTv.setText(title);
        return this;
    }

    /**
     * 设置右边的点击事件
     */
    public TitleBar setRightOnClickListener(View.OnClickListener listener){
        if(rightIv.getVisibility()==View.VISIBLE){
            rightIv.setOnClickListener(listener);
        }else if(rightTv.getVisibility()==View.VISIBLE){
            rightTv.setOnClickListener(listener);
        }
        return this;
    }


}
