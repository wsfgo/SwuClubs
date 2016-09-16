package com.example.swuclubmanage.com.wsf.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.adapter.ViewPagerAdapter;
import com.example.swuclubmanage.com.wsf.com.wsf.fragment.AllClubFragment;
import com.example.swuclubmanage.com.wsf.com.wsf.fragment.GoodClubFragment;
import com.example.swuclubmanage.com.wsf.utils.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class ViewAllClubActivity extends AppCompatActivity {
     private TabLayout tabLayout;
     private ViewPager viewPager;
     private List<Fragment>  list_fragment=new ArrayList<>();
     private List<String> list_title=new ArrayList<>();
     private ViewPagerAdapter adapter;
     private AllClubFragment allClubFragment=new AllClubFragment();
     private GoodClubFragment goodClubFragment=new GoodClubFragment();
      @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_all_club);
        initTitleBar();

        tabLayout= (TabLayout) findViewById(R.id.tl);
        viewPager= (ViewPager) findViewById(R.id.viewpager);

      list_fragment.add(allClubFragment);
        list_fragment.add(goodClubFragment);

        list_title.add("全部社团");
        list_title.add("优秀社团");


        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));
        adapter=new ViewPagerAdapter(getSupportFragmentManager(),list_fragment,list_title);


       viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        //MODE_FIXED:固定tabs，并同时显示所有的tabs。
        //MODE_SCROLLABLE：适用于多屏展示的页卡选项，并不会把所有的tab全部显示出来，会根据title的长度来显示tab的宽度。
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //设置tab文字的显示效果：
        //TabLayout.GRAVITY_FILL ：填充整个tab，必须和MODE_FIXED模式一起使用才会见效。
        //TabLayout.GRAVITY_CENTER ：tab文字居中显示
        //不设置TabGravity时，默认是GRAVITY_FILL。
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //设置文字选中和没有选中的颜色
        tabLayout.setTabTextColors(getResources().getColor(R.color.c_333333),getResources().getColor(R.color.c_e53333));
        //设置下划线颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.c_e53333));
        //设置下划线高度，宽度跟随tab的宽度
        tabLayout.setSelectedTabIndicatorHeight(5);
    }
    private void initTitleBar() {
        new TitleBar(this).setTitle("查看社团")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}
