package com.example.swuclubmanage.com.wsf.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.adapter.TestAdapter;
import com.example.swuclubmanage.com.wsf.utils.RecyItemDirection;

import java.util.ArrayList;
import java.util.List;

public class SwipRefreshTest extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TestAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip_refresh_test);
        initView();
    }

    private void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView_test);
        adapter=new TestAdapter(this,getRecycleData(10));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecyItemDirection(this, RecyItemDirection.VERTICAL_LIST));
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.refresh);
        initSwipRefeeshLayout();
    }

    public void initSwipRefeeshLayout(){
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       adapter.addItems(getRecycledata(3));
                       adapter.notifyItemChanged(0,10);
                        recyclerView.scrollToPosition(0);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);

            }
        });
    }


    public List<String> getRecycleData(int count){
        List<String>addData=new ArrayList<>();
        for(int i=0;i<count;i++){
            addData.add("呵呵哒+_+");
        }
        return addData;
    }
    public List<String> getRecycledata(int count){
        List<String>addData=new ArrayList<>();
        for(int i=0;i<count;i++){
            addData.add("王书发");
        }
        return addData;
    }


}
