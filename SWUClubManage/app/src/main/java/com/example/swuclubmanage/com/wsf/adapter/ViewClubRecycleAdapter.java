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
import com.example.swuclubmanage.com.wsf.utils.ImageMotify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王书发 on 2016/6/3.
 */
public class ViewClubRecycleAdapter extends RecyclerView.Adapter<ViewClubRecycleAdapter.MyViewHolder> {
    private Context context;
    //社团名称
    private List<String> clubName=null;
    //社团星级
    private List<String> clubStar=null;

    //社团的评价
    private List<String> clubDescrb=null;
    //社团部长
    private List<String> clubMinister=null;
    //社团图片
    private List<Bitmap> clubImgBit=new ArrayList<>();
    //屏幕的宽高
    private int screenWidth,screenHeight=0;

    //图片缩放比例
    private final static float scaleWidth=1.5f;
    private final static float scaleHeight=1.3f;

    ;

    public ViewClubRecycleAdapter(List<Bitmap> clubImg, List<String> clubName, List<String> clubStar,List<String>clubDescrb, List<String> clubMinister,Context context, int screenHeight, int screenWidth) {
        this.clubImgBit = clubImg;
        this.clubName = clubName;
        this.clubStar = clubStar;
        this.clubDescrb=clubDescrb;
        this.clubMinister=clubMinister;
        this.context = context;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.all_club_item,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
      Bitmap bitmap=clubImgBit.get(position);
      //  bitmap=(new ImageMotify(bitmap, context, screenWidth, screenHeight)).mitifyImageSize(scaleWidth, scaleHeight);
        holder.imageView.setImageBitmap(bitmap);
        holder.clubname.setText(clubName.get(position));
        holder.clubstarvalue.setText(clubStar.get(position));
        holder.clubministerValue.setText(clubMinister.get(position));
        holder.clubdescrib.setText(clubDescrb.get(position));

        if(onItemClickListener !=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position=holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView,position);
                    return false;
                }
            });

        }
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return clubName.size();
    }


    public interface  OnItemClickListener{
        void onItemClick(View view,int position);

        void onItemLongClick(View view,int position);
    }

  private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        //社团名字
        private TextView clubname;
        //社团星级
        private TextView clubstarvalue;
       //社团的简单描述
        private TextView clubdescrib;
       //社团的图片
        private ImageView imageView;
        //社团部长
        private TextView clubministerValue;
        public MyViewHolder(View itemView) {
            super(itemView);
            clubname= (TextView) itemView.findViewById(R.id.clubname_value);
            clubstarvalue=(TextView)itemView.findViewById(R.id.clubstarvalue);
            clubdescrib= (TextView) itemView.findViewById(R.id.clubdescrib);
            imageView= (ImageView) itemView.findViewById(R.id.clubImg);
            clubministerValue= (TextView) itemView.findViewById(R.id.clubministerValue);
        }
    }
}
