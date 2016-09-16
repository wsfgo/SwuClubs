package com.example.swuclubmanage.com.wsf.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.utils.SimpleImageLoadingListener;
import com.example.swuclubmanage.com.wsf.utils.TitleBar;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.jude.rollviewpager.hintview.TextHintView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ViewJutiActivity extends AppCompatActivity {

      private RollPagerView rollPagerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Bitmap [] bitmap=new Bitmap[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.viewnews_juti);
        initTitleBar();
        TextView newsNameTxt= (TextView) findViewById(R.id.textView21);
        TextView newsContent= (TextView) findViewById(R.id.newsContent);
        rollPagerView= (RollPagerView) findViewById(R.id.mynewsjuti);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.viewjutiswipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent=new Intent(ViewJutiActivity.this,ViewJutiActivity.class);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setRefreshing(false);
        //得到轮播图片
        String[] imgUrl={"http://c.hiphotos.baidu.com/image/h%3D200/sign=d0da57b5cd95d143c576e32343f18296/03087bf40ad162d960aed9aa14dfa9ec8b13cdc0.jpg","http://b.hiphotos.baidu.com/image/pic/item/64380cd7912397dd103818b05c82b2b7d1a287c5.jpg",
                "http://img2.imgtn.bdimg.com/it/u=29084753,959280795&fm=11&gp=0.jpg"     };

        for(int i=0;i<imgUrl.length;i++){
            bitmap[i]=downloadimg(imgUrl[i],i);
        }

        //设置播放时间间隔
        rollPagerView.setPlayDelay(1500);
        //设置透明度
        rollPagerView.setAnimationDurtion(500);
        //设置适配器


        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        rollPagerView.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));
        rollPagerView.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
        rollPagerView.setAdapter(new TestNormalAdapter());

        Intent intent=getIntent();

        String newsclub=intent.getStringExtra("newsname");
        String newscontent=intent.getStringExtra("newscontent");

        newsNameTxt.setText(newsclub);
        newsContent.setText(newscontent);
    }

    public Bitmap downloadimg(String imgUrl, final int i){

        //ImageSize mImageSize = new ImageSize(100, 100);

        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisc(true)
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

    private class TestNormalAdapter extends StaticPagerAdapter {

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());

            view.setImageBitmap(bitmap[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getCount() {
            return bitmap.length;
        }

    }
    private void initTitleBar() {
        new TitleBar(ViewJutiActivity.this).setTitle("最新社团消息")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}
