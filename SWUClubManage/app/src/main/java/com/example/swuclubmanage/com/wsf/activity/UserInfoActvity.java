package com.example.swuclubmanage.com.wsf.activity;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.bean.UserInfo;
import com.example.swuclubmanage.com.wsf.utils.SimpleImageLoadingListener;
import com.example.swuclubmanage.com.wsf.utils.TitleBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserInfoActvity extends AppCompatActivity {
    List<UserInfo> newsList;
    Bitmap bitmap;
   private Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           if(msg.what==1) {
               UserInfo userInfo = newsList.get(0);
               TextView studentname= (TextView) findViewById(R.id.studentnamewsf);

               TextView userInfoEmailValue= (TextView) findViewById(R.id.userInfoEmailValue);


               TextView userInfoAddress= (TextView) findViewById(R.id.userAddressValue);

               TextView userInfoGoalsValue= (TextView) findViewById(R.id.userInfoGoalsValue);

               TextView userGenderValue= (TextView) findViewById(R.id.userGenderValue);

               TextView userBirthValue= (TextView) findViewById(R.id.userBirthValue);
               studentname.setText(userInfo.getName());
               userInfoEmailValue.setText(userInfo.getMail());
               userInfoAddress.setText(userInfo.getMyclass());
               userInfoGoalsValue.setText(userInfo.getZuoyouming());
               userGenderValue.setText(userInfo.getGender());
               userBirthValue.setText(userInfo.getAge());
             //  userinfoTxt.setText(userInfo.toString());


               ImageView userNameImg= (ImageView) findViewById(R.id.userNameImg);
               bitmap=downloadimg(userInfo.getImage());
             userNameImg.setImageBitmap(bitmap);
           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_info);
        getNewsInfo();
        final View title=findViewById(R.id.myinfotitle);
    }


    public Bitmap downloadimg(String imgUrl){

        //ImageSize mImageSize = new ImageSize(100, 100);

        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().loadImage(imgUrl, options, new SimpleImageLoadingListener() {

            @Override
            public void onLoadingComplete(String imageUri, View view,
                                          Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                bitmap = loadedImage;
            }

        });
        return bitmap;
    }
    private void getNewsInfo() {
        Thread t = new Thread(){
            @Override
            public void run() {
                String path = "http://106.88.174.142:8080/news.xml";
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);

                    if(conn.getResponseCode() == 200){
                        //流里的信息是一个xml文件的文本信息，用xml解析器去解析，而不要作为文本去解析
                        InputStream is = conn.getInputStream();
                        getNewsFromStream(is);

                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    private void getNewsFromStream(InputStream is) {
        XmlPullParser xp = Xml.newPullParser();
        try {
            xp.setInput(is, "utf-8");

            //获取事件类型，通过事件类型判断出当前解析的是和什么节点
            int type = xp.getEventType();

            UserInfo news = null;
            while(type != XmlPullParser.END_DOCUMENT){
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if("newslist".equals(xp.getName())){
                            newsList = new ArrayList<UserInfo>();
                        }
                        else if("student".equals(xp.getName())){
                            news = new UserInfo();
                        }
                        else if("name".equals(xp.getName())){
                            String title = xp.nextText();
                            news.setName(title);
                        }
                        else if("belife".equals(xp.getName())){
                            String detail = xp.nextText();
                            news.setZuoyouming(detail);
                        }
                        else if("age".equals(xp.getName())){
                            String comment = xp.nextText();
                            news.setAge(comment);
                        }
                        else if("gender".equals(xp.getName())){
                            String comment = xp.nextText();
                            news.setGender(comment);
                        }
                        else if("mail".equals(xp.getName())){
                            String comment = xp.nextText();
                            news.setMail(comment);
                        } else if("myclass".equals(xp.getName())){
                            String comment = xp.nextText();
                            news.setMyclass(comment);
                        } else if("hoppy".equals(xp.getName())){
                            String comment = xp.nextText();
                            news.setHoppy(comment);
                        }
                        else if("image".equals(xp.getName())){
                            String image = xp.nextText();
                            news.setImage(image);
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if("student".equals(xp.getName())){
                            newsList.add(news);
                        }
                        break;

                }
                //指针移动到下一个节点并返回事件类型
                type = xp.next();
            }

            //发送消息，让主线程刷新listview
            handler.sendEmptyMessage(1);
//			for (News n : newsList) {
//				System.out.println(n.toString());
//			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
