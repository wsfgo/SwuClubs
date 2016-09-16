package com.example.swuclubmanage.com.wsf.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.adapter.GoodClubAdapter;
import com.example.swuclubmanage.com.wsf.utils.SimpleImageLoadingListener;
import com.example.swuclubmanage.com.wsf.utils.TitleBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyClubActivity extends AppCompatActivity {
     RecyclerView recyclerView;
    Bitmap[] bitmap=new Bitmap[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_club);

        ImageView imageView= (ImageView) findViewById(R.id.myclubback);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        GoodClubAdapter adapter=new GoodClubAdapter(this,getClubDes(),getClubImg(),getClubName());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
       // initTitleBar();
    }

    public List<Bitmap> getClubImg(){
        List<Bitmap> goodclubimg=new ArrayList<>();
        String imgUrl="http://www.yuebaozhai.net/upFile/pic/2013_4_13_253681.jpg";
        goodclubimg.add(downloadimg(imgUrl, 0));
        String imgUrl1="http://img0.imgtn.bdimg.com/it/u=610834575,3844552579&fm=21&gp=0.jpg";
        goodclubimg.add(downloadimg(imgUrl1, 1));
        String imgUrl2="http://www.bashu.com.cn/UpLoadFiles/Images/2013/10/14/201310140042.jpg";
        goodclubimg.add(downloadimg(imgUrl2, 2));
        String imgUrl13="http://img3.imgtn.bdimg.com/it/u=708471758,2317827499&fm=21&gp=0.jpg";
        goodclubimg.add(downloadimg(imgUrl13, 3));
        return goodclubimg;
    }


    public List getClubDes(){
        List<String> goodclubDes=new ArrayList<>();
        goodclubDes.add("书法社团，勇闯第一，优秀源于细节");
        goodclubDes.add("音乐社团，在动人的歌声中成长");
        goodclubDes.add("在辩论中体会语言的魅力");
        goodclubDes.add("影视欣赏，欣赏影视");
        return goodclubDes;
    }

    public List getClubName(){
        List<String> goodClubName=new ArrayList<>();
        goodClubName.add("书法社团");
        goodClubName.add("音乐社团");
        goodClubName.add("辩论社团");
        goodClubName.add("影视社团");
        return goodClubName;
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

}
