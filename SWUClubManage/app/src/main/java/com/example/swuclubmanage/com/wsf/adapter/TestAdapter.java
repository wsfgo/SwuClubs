package com.example.swuclubmanage.com.wsf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swuclubmanage.R;

import java.util.List;

/**
 * Created by 王书发 on 2016/9/9.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private Context context;
    private List<String> dataList;

    public TestAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.test_item, null);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
              holder.mTextView.setText(dataList.get(position));
        holder.mTextView.setTextColor(context.getResources().getColor(android.R.color.holo_blue_bright));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    public void addItems(List<String> newdata){
        if(newdata!=null){
          dataList.addAll(newdata);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView.findViewById(R.id.heheda);
        }
    }
}
