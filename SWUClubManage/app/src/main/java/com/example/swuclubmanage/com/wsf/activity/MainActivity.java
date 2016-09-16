package com.example.swuclubmanage.com.wsf.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.adapter.ClubNewsAdapter;
import com.example.swuclubmanage.com.wsf.utils.CircleImageView;
import com.example.swuclubmanage.com.wsf.utils.SimpleImageLoadingListener;
import com.example.swuclubmanage.com.wsf.utils.SpaceItemDecoration;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.jude.rollviewpager.hintview.TextHintView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RollPagerView mRollViewPager;


    Bitmap [] imgs=new Bitmap[4];
    Bitmap [] bitmap=new Bitmap[10];
    List<String> clubnewsImg=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
         final ImageView refreshImg= (ImageView) findViewById(R.id.mainrefresh);
        String imagsUrl1 = "http://pic2.nipic.com/20090408/1403492_194322002_2.jpg";
    imgs[0]=downloadimg(imagsUrl1,5);
        String imgsUrl2="http://img5.imgtn.bdimg.com/it/u=3425851328,2681317699&fm=21&gp=0.jpg";
        imgs[1]=downloadimg(imgsUrl2,6);
        String imgsUrl3="http://img10.3lian.com/sc6/show02/38/65/386515.jpg";
        imgs[2]=downloadimg(imgsUrl3,7);
        String imgsUrl4="http://pic37.nipic.com/20140209/8821914_163234218136_2.jpg";
        imgs[3]=downloadimg(imgsUrl4,8);


       refreshImg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Animation operatingAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tip);
               LinearInterpolator lin = new LinearInterpolator();
               operatingAnim.setInterpolator(lin);
               refreshImg.setAnimation(operatingAnim);
               refreshImg.startAnimation(operatingAnim);
               Intent intent=new Intent(MainActivity.this,MainActivity.class);
               startActivity(intent);
               finish();
           }
       });


  RecyclerView recyclerView= (RecyclerView) findViewById(R.id.newsclubRecy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ClubNewsAdapter newsadapter=new ClubNewsAdapter(this,getNewsImg(),getNesName(),getNesContent(),getnewsTime());
        int spacingInPixels = 12;
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyclerView.setAdapter(newsadapter);
        newsadapter.notifyDataSetChanged();


        final String[] clubnewscontent={   "       为了弘扬书法艺术，促进校园文化建设。我校书法协会协会于 2013年4月25日特举办“墨香杯”书法比赛的团日活动。  4月25日中午午12:00—14:00在校团委和大学生社团联合会的指导下，由校书法协会承办的“墨香杯”书法比赛在图书馆前广场展开。书法比赛是一项旨在更好的展示中国书法文化，丰富校园文化建设，构建和谐校园的有意义的活动。本次活动共分三项内容，分别为：赛前作品征集，现场书写作品，最后作品评比。  通过本次活动我们学校的广大书法爱好者挥毫泼墨书写啦对书法艺术的热爱，用墨香的味道装扮啦我们春季的校园，也进一步宣传啦书法艺术",
                                         "       “领悟音乐，唱响青春”社团音乐会在濂溪校区篮球场成功举办。经济管理学院、建筑工程学院学生社团以及部分校级社团共同参与了演出，为同学们带来一场精彩绝伦的音乐盛宴。\n" +
                                                 "　　音乐会一开始，一首动听的《青花瓷》便瞬间点燃了现场的气氛，那优美动听的旋律，博得了现场观众的阵阵掌声。紧接着各种类型的节目轮番登场，精彩纷呈。由风随我动轮滑社带来的舞蹈《爱的华尔兹》将音乐会推向高潮，演出同学那轻灵而娴熟的舞姿，感染了在场的所有观众。J舞堂带来的街舞《old school》动感四射，充分表现出大学生的青春活力。",
      "           经过11月22日的初赛，共有17位选手进入决赛。决赛第一阶段以选手自命题演讲的形式进行，围绕大学生活、个人意志等话题进行自主发挥。通过激烈角逐，共九名选手晋级第二轮“即兴演讲”环节。选手对双十一网购、开卷闭卷考试、免费使用WiFi等话题发表演讲并接受评委提问。比赛现场气氛紧张，选手们精彩的发言和标准的口语赢得现场同学阵阵掌声和评委老师的好评。经过两轮激烈角逐，李长海、熊晨宇、姜美辰三名选手分别获一、二、三等奖",
                "         此次徒步活动分为5公里和10公里分组比赛方式，大家以饱满的热情参与其中，争分夺秒，互相鼓励，挑战自我以最快的速度走完了全程。两项比赛的冠军分别由5公里第5组和10公里第6组夺得。"
        };
        final String[] newsclubname=clubnewsImg.toArray(new String[clubnewsImg.size()]);


        //设置点击事件
        newsadapter.setOnItemClickListener(new ClubNewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点了" + position + "项", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,ViewJutiActivity.class);
                intent.putExtra("newsname",newsclubname[position]);
                intent.putExtra("newscontent",clubnewscontent[position]);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRollViewPager= (RollPagerView) findViewById(R.id.myroll_view_pager);

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(1500);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器


        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW,Color.WHITE));
        mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);

      //  setSupportActionBar(toolbar);
        TestNormalAdapter adapter=new TestNormalAdapter();
        mRollViewPager.setAdapter(adapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView main_back= (ImageView) findViewById(R.id.main_back);
        main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    public List<Bitmap> getNewsImg(){
        List<Bitmap> clubnewsImg=new ArrayList<>();
        String imgUrl="http://img5.imgtn.bdimg.com/it/u=2935066762,2972595197&fm=21&gp=0.jpg";
        clubnewsImg.add(downloadimg(imgUrl,0));
        String imgUrl1="http://pic18.nipic.com/20111126/8922399_104430949000_2.jpg";
        clubnewsImg.add(downloadimg(imgUrl1,1));

        clubnewsImg.add(imgs[0]);
        String imgUrl2="http://img5.imgtn.bdimg.com/it/u=234513892,309969409&fm=21&gp=0.jpg";
        clubnewsImg.add(downloadimg(imgUrl2,3));
        return clubnewsImg;
    }


    public List<String> getNesName(){

        clubnewsImg.add("书法社团新闻");
        clubnewsImg.add("音乐社团新闻");
        clubnewsImg.add("演讲与辩论社团新闻");
        clubnewsImg.add("健身社团新闻");
        return clubnewsImg;
    }

    public List<String> getNesContent(){
        List<String> clubnewsContent=new ArrayList<>();
        clubnewsContent.add("      书法社团最近在四运举行了毛笔字切磋大赛，看他们专注的眼神！");
        clubnewsContent.add("      音乐社团成功举办第六节校园歌手大赛！");
        clubnewsContent.add("      演讲社团参加了校园辩论大赛，取得了辉煌成绩！");
        clubnewsContent.add("      书法社团最近在四运举行了毛笔字切磋大赛，看他们专注的眼神！");
        return clubnewsContent;
    }


    public List<String> getnewsTime(){
        List<String> clubnewsImg=new ArrayList<>();
        clubnewsImg.add("2016-6-1");
        clubnewsImg.add("2016-6-11");
        clubnewsImg.add("2016-6-11");
        clubnewsImg.add("2016-6-11");
        return clubnewsImg;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent=new Intent(MainActivity.this, ViewAllClubActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            Intent intent=new Intent(MainActivity.this,MyClubActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent=new Intent(MainActivity.this,SwipRefreshTest.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent=new Intent(MainActivity.this,SeetingActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent=new Intent(MainActivity.this,AboutUsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent=new Intent(MainActivity.this,UserInfoActvity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        CircleImageView circleImageView= (CircleImageView) findViewById(R.id.studentImg);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }




    private class TestNormalAdapter extends StaticPagerAdapter {

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());

            view.setImageBitmap(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getCount() {
            return imgs.length;
        }
    }





    public Bitmap downloadimg(String imgUrl, final int i){

        //ImageSize mImageSize = new ImageSize(100, 100);
        File cacheDir = StorageUtils.getCacheDirectory(this);
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)

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

}
