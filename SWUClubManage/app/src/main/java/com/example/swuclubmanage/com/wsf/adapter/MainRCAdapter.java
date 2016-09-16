package com.example.swuclubmanage.com.wsf.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.swuclubmanage.R;

import java.util.List;

/**
 * Created by wsf on 2016/4/5.
 */
public class MainRCAdapter extends RecyclerView.Adapter<MainRCAdapter.MyViewHolder> {
    //推荐课程图片
    private List<Bitmap> bitmapList;
    //推荐课程的名字
    private List<String> nameCourseFromRE;
    private Context context=null;

    public MainRCAdapter(Context context, List<Bitmap> bitmapList, List<String> nameCourseFromRE){
        this.context=context;
        this.nameCourseFromRE=nameCourseFromRE;
        this.bitmapList=bitmapList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_recycleview_item,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.re_course_img_frist.setImageBitmap(bitmapList.get(position));
      //  holder.re_course_img_second.setImageBitmap(bitmapList.get(position));
        holder.re_course_name.setText(nameCourseFromRE.get(position));
       // holder.re_course_name_two.setText(nameCourseFromRE.get(position));
    }



    @Override
    public int getItemCount() {
        return bitmapList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        //推荐课程图片1
     private ImageView re_course_img_frist=null;
        private ImageView re_course_img_second=null;
        //推荐课程名字
        private TextView re_course_name=null;
       private TextView re_course_name_two=null;
        public MyViewHolder(View itemView) {
            super(itemView);
            re_course_img_frist=(ImageView)itemView.findViewById(R.id.fristcourse);
        //    re_course_img_second=(ImageView)itemView.findViewById(R.id.secondcourse);
            re_course_name=(TextView)itemView.findViewById(R.id.frist_course_name);
           // re_course_name_two=(TextView)itemView.findViewById(R.id.secone_course_name);;
        }
    }

}
