package com.example.swuclubmanage.com.wsf.com.wsf.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.activity.ClubDescribActivity;
import com.example.swuclubmanage.com.wsf.activity.ViewAllClubActivity;
import com.example.swuclubmanage.com.wsf.adapter.ViewClubRecycleAdapter;
import com.example.swuclubmanage.com.wsf.utils.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王书发 on 2016/5/26.
 */
public class AllClubFragment extends Fragment {
    private ViewClubRecycleAdapter adapter;
    Bitmap[] bitmap=new Bitmap[15];
    private List<String> clubName=new ArrayList<>();
    //社团星级
    private List<String> clubStar=new ArrayList<>();
    //社团部长
    private List<String> clubMinister=new ArrayList<>();
    //社团的评价
    private List<String> clubDescrb=new ArrayList<>();
    //社团图片
    private List<Bitmap> clubImgBit=new ArrayList<>();

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    boolean isLoading;

    private Handler handler=new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DisplayMetrics metrics=new DisplayMetrics();
        (getActivity().getWindowManager()).getDefaultDisplay().getMetrics(metrics);
      int  screenWidth=metrics.widthPixels;
      int   screenHeight=metrics.heightPixels;
        View view=inflater.inflate(R.layout.all_club,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        initData();
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setColorSchemeColors(R.color.blueStatus);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              //  FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //transaction.remove(AllClubFragment.this);
                //transaction.add(new AllClubFragment(),"start");
               // transaction.addToBackStack(null);
                Intent intent=new Intent(getActivity(),ViewAllClubActivity.class);
                startActivity(intent);
                getActivity().finish();

                for(int i=0;i<8;i++){
                    clubImgBit.add(downloadimg(imgurl[i],i));
                }
             recyclerView.setAdapter(adapter);
             swipeRefreshLayout.setRefreshing(false);
            }
        });


   final   LinearLayoutManager   layoutManager=new LinearLayoutManager(getActivity());
        adapter=new ViewClubRecycleAdapter(addClubImg(),addClubName(), addClubStar(),addClubDescrib(),addClubMinister(),getActivity(),screenWidth,screenHeight);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                               getData();
                                Log.d("test", "load more completed");
                                isLoading = false;
                            }
                        }, 2400);
                    }
                }
            }
        });
       final String[]  clubNameForDescribe={"音乐社团","Talking社","书法社团","学生集邮协会","演讲与辩论协会","影视协会","紫韵茶艺社","健身协会"};
        final String[] clubmasterliuyan={"认真   负责    坚持   爱音乐","我们有和善的团队，期待优秀的你的加入","喜欢书法  因为觉得书法很帅" ,"珍惜从小时候就有的爱好","一起进步，共同前进","一起进步，共同前进","一起进步，共同前进","一起进步，共同前进","一起进步，共同前进"};
        final String[] clubDescribejuti={"      西南大学亲音社是为了给广大音乐爱好者和歌迷提供一个欣赏音乐，品味音乐的空间以及展示音乐才能和个人风采的舞台，是为了给歌迷们提供互相交流，互相促进的机会，寻找音乐朋友，提高音乐品味，提高对音乐及其他文艺素质的修养。",
                "重在在广大学子中推广普通话，发扬普通话，以及交流一切与语言有关的知识、技巧等，增强广大学子的交流能力及表达能力，更为喜欢主持、采访等工作的同学提供专业的指导和帮助",
                "       西南大学书法协会，以增长书法知识、交流书法经验，丰富课余生活，活跃校园文化氛围，提高文化艺术修养为宗旨，开展定期举办书法培训，新生书法大赛，规范汉字现场书写比赛等各项活动，提高了会员的书写技能和创作水平。",
             "学生集邮协会，以“团结在校集邮以及其它收藏爱好者，传播集邮文化知识，倡导集邮研究，繁荣校园文化，推动校风建设”为宗旨，开展形式多样有意义的集邮活动和集邮学术研究，传播集邮知识，倡导高尚的集邮道德。",
                "演讲辩论协会具有专业性强，受众面广，实用性大，趣味性高的突出特色，以开展演讲、辩论、朗诵、讲座、主持人培训、普通话培训、社交礼仪培训等大学生喜闻乐见的活动，为西大学子提供广阔的思想交流平台和才华展示空间，切实提高同学们的语言表达能力，思辩处世能力和社会交往能力",
                "学生集邮协会，以“团结在校集邮以及其它收藏爱好者，传播集邮文化知识，倡导集邮研究，繁荣校园文化，推动校风建设”为宗旨，开展形式多样有意义的集邮活动和集邮学术研究，传播集邮知识，倡导高尚的集邮道德。",
                "演讲辩论协会具有专业性强，受众面广，实用性大，趣味性高的突出特色，以开展演讲、辩论、朗诵、讲座、主持人培训、普通话培训、社交礼仪培训等大学生喜闻乐见的活动，为西大学子提供广阔的思想交流平台和才华展示空间，切实提高同学们的语言表达能力，思辩处世能力和社会交往能力",
                "影视协会，以“学习影视专业知识，丰富校园课余活动，提高影视欣赏水平，培养健康审美情趣”为宗旨，开展一系列的定期的影视观摩，影评大赛，名师讲座等活动，还举行首届校园影视文化节，重庆市首届先锋青年影像大赛，极大地丰富了全员的影视知识 "
        };
       //设置监听事件
        adapter.setOnItemClickListener(new ViewClubRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               // Toast.makeText(getActivity(),"点击了第"+position+"项",Toast.LENGTH_SHORT).show();
                Context context=getActivity().getApplicationContext();
                Intent intent=new Intent(context, ClubDescribActivity.class);
                intent.putExtra("imgurl", imgurl[position]);
                intent.putExtra("clubName",clubNameForDescribe[position]);
                intent.putExtra("clubDescrib",clubDescribejuti[position]);
                intent.putExtra("clubmasterliuyan",clubmasterliuyan[position]);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public List<String> addClubName(){
        List<String> clubName=new ArrayList<>();
        clubName.add("音乐社团");
        clubName.add("Talking社");
        clubName.add("书法社团");
        clubName.add("学生集邮协会");
        clubName.add("演讲与辩论协会");
        clubName.add("影视协会");
        clubName.add("紫韵茶艺社");
        clubName.add("健身协会");

     ;

        return clubName;
    }

   public Bitmap downloadimg(String imgUrl, final int i){

       //ImageSize mImageSize = new ImageSize(100, 100);

       //显示图片的配置
       DisplayImageOptions options = new DisplayImageOptions.Builder()
               .cacheInMemory(true)
               .cacheOnDisc(true)

               .bitmapConfig(Bitmap.Config.RGB_565)
               .build();

       ImageLoader.getInstance().loadImage(imgUrl, options, new SimpleImageLoadingListener() {

           @Override
           public void onLoadingComplete(String imageUri, View view,
                                         Bitmap loadedImage) {
               super.onLoadingComplete(imageUri, view, loadedImage);
               bitmap[i] = loadedImage;
           }

       });
       return bitmap[i];
   }
    String[]imgurl={ "http://s1.sinaimg.cn/bmiddle/5f16094ch64e978174890", "http://img5.imgtn.bdimg.com/it/u=3771223798,2557134977&fm=15&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1719252534,3324188211&fm=21&gp=0.jpg", "http://res.cngoldres.com/upload/2014/1103/e3132db17f097529743178a504f7a4cd.jpg",
            "http://img5.imgtn.bdimg.com/it/u=562116074,1957400169&fm=21&gp=0.jpg", "http://img0.imgtn.bdimg.com/it/u=650392253,525913705&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3431656393,516925567&fm=21&gp=0.jpg", "http://img5.imgtn.bdimg.com/it/u=3328316533,952129168&fm=21&gp=0.jpg"
    };

    public List<Bitmap>addClubImg(){
     for(int i=0;i<8;i++){
         clubImgBit.add(downloadimg(imgurl[i],i));
     }

        return  clubImgBit;
    }

    public List<String>addClubStar(){
        List<String> clubStar=new ArrayList<>();
        clubStar.add("5星级");
        clubStar.add("4星级");
        clubStar.add("5星级");
        clubStar.add("4星级");
        clubStar.add("5星级");
        clubStar.add("4星级");
        clubStar.add("5星级");
        clubStar.add("4星级");
        return clubStar;
    }

    public List<String> addClubMinister(){
        List<String> clubMinister=new ArrayList<>();
        clubMinister.add("王书发");
        clubMinister.add("周杰伦");
        clubMinister.add("宋仲基");
        clubMinister.add("鹿晗");
        clubMinister.add("Baby");
        clubMinister.add("天空");
        clubMinister.add("宋仲基");
       clubMinister.add("鹿晗");

        return  clubMinister;
    }

   public List<String>addClubDescrib(){
       List<String> clubDescrb=new ArrayList<>();
       clubDescrb.add("我们有专业的态度，有负责的教师，加入我们，一起玩音乐");
       clubDescrb.add("为喜欢主持、采访等工作的同学提供专业的指导和帮助");
       clubDescrb.add("如果你热爱书法，热爱写作，快来加入我们吧");
       clubDescrb.add( "团结在校集邮以及其它收藏爱好者，传播集邮文化知识，倡导集邮研究");
       clubDescrb.add( "开展演讲、辩论、朗诵、讲座、主持人培训、普通话培训、社交礼仪培训");
       clubDescrb.add("学习影视专业知识，丰富校园课余活动，提高影视欣赏水平，培养健康审美情趣");
       clubDescrb.add("以继承和发扬中国茶文化、振兴中国茶道为已任，以拓宽同学们的知识面");
       clubDescrb.add("不仅可以获得健身的知识，还可以获得快乐的健身体验");



       return clubDescrb;
   }

    public void  getData(){
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyItemRemoved(adapter.getItemCount());
    }
    public void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 1500);

    }
}
