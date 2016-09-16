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
 * Created by 王书发 on 2016/6/12.
 */
public class ClubNewsAdapter extends RecyclerView.Adapter<ClubNewsAdapter.MyViewHolder> {

    Context context;
    List<Bitmap> newsClubImg;
    List<String> newsClubName;
    List<String>newsContent;
    List<String>newsTime;

    public ClubNewsAdapter(Context context, List<Bitmap> newsClubImg, List<String> newsClubName, List<String> newsContent, List<String> newsTime) {
        this.context = context;
        this.newsClubImg = newsClubImg;
        this.newsClubName = newsClubName;
        this.newsContent = newsContent;
        this.newsTime = newsTime;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.clubnews_item,null);
       MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Bitmap bitmap=newsClubImg.get(position);
      holder.newsclubimg.setImageBitmap(bitmap);
        holder.newsclubName.setText(newsClubName.get(position));
        holder.newsContent.setText(newsContent.get(position));
        holder.newTime.setText(newsTime.get(position));

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
        return newsClubName.size();
    }


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
         //协会新闻图片
        ImageView newsclubimg;
        //协会新闻名字
        TextView newsclubName;
        //新闻内容
        TextView newsContent;
        //发布时间
        TextView newTime;
        public MyViewHolder(View itemView) {
            super(itemView);
            newsclubimg= (ImageView) itemView.findViewById(R.id.newsclunimg);
            newsclubName= (TextView) itemView.findViewById(R.id.clubnewsName);
            newsContent= (TextView) itemView.findViewById(R.id.club_news_content);
            newTime= (TextView) itemView.findViewById(R.id.newstime);
        }
    }

    public interface  OnItemClickListener{
        void onItemClick(View view,int position);

        void onItemLongClick(View view,int position);
    }
}
