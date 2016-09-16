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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王书发 on 2016/6/14.
 */
public class GoodClubAdapter extends RecyclerView.Adapter<GoodClubAdapter.MyViewHolder> {
    private Context context;
    //优秀社团图片
    List<Bitmap> goodClubImgList=new ArrayList<Bitmap>();
    //优秀社团描述
    List<String> goodclubdes;
    //优秀社团名称
    List<String> goodClubName;

    public GoodClubAdapter(Context context, List<String> goodclubdes, List<Bitmap> goodClubImg, List<String> goodClubName) {
        this.context = context;
        this.goodclubdes = goodclubdes;
        this.goodClubImgList = goodClubImg;
        this.goodClubName = goodClubName;
    }

    @Override
    public GoodClubAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.goodclub_item,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(GoodClubAdapter.MyViewHolder holder, int position) {
        Bitmap bitmap=goodClubImgList.get(position);
        holder.goodclubImg.setImageBitmap(bitmap);
        holder.goodclubDes.setText(goodclubdes.get(position));
        holder.goodclubName.setText(goodClubName.get(position));
    }

    @Override
    public int getItemCount() {
        return goodClubName.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView goodclubImg;
        private TextView goodclubDes;
        private TextView goodclubName;

        public MyViewHolder(View itemView) {
            super(itemView);
            goodclubImg= (ImageView) itemView.findViewById(R.id.goodclubimg);
            goodclubDes= (TextView) itemView.findViewById(R.id.goodclubdescrib);
            goodclubName= (TextView) itemView.findViewById(R.id.goodclubname);

        }
    }

}
