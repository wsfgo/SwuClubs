package com.example.swuclubmanage.com.wsf.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.bean.ClubInfo;
import com.example.swuclubmanage.com.wsf.utils.GetCurrentTime;
import com.example.swuclubmanage.com.wsf.utils.SimpleImageLoadingListener;
import com.example.swuclubmanage.com.wsf.utils.TitleBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ClubDescribActivity extends AppCompatActivity {
    Bitmap bitmap;
    ClubInfo clubInfo;
    String clubName;
    String  imgUrl;
    String clubDes;
    String liuyan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_club);
        ImageView clubImg= (ImageView) findViewById(R.id.clubnameDescribewsf);
        TextView textView= (TextView) findViewById(R.id.clubNameFromOther);
        TextView clubdescribe= (TextView) findViewById(R.id.clubdescrib_juti1);
        ImageView mybackimg= (ImageView) findViewById(R.id.mybackimg);
        TextView clubmasterliuyan= (TextView) findViewById(R.id.clubmasterliuyan);
        TextView baomingimg= (TextView) findViewById(R.id.baomingimg);





        mybackimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        final Intent intent=getIntent();
         imgUrl=intent.getStringExtra("imgurl");
         clubName=intent.getStringExtra("clubName");
         clubDes=intent.getStringExtra("clubDescrib");
         liuyan=intent.getStringExtra("clubmasterliuyan");
        textView.setText(clubName);
        clubdescribe.setText(clubDes);
        clubmasterliuyan.setText(liuyan);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        final AlertDialog dialog=new AlertDialog.Builder(this).create();
        dialog.setTitle("报名");
        dialog.setIcon(R.mipmap.ic_baoming);
        dialog.setMessage("确认报名");
        dialog.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
           Intent intent1=new Intent(ClubDescribActivity.this,MyClubActivity.class);
                intent1.putExtra("clubname",clubName);
                intent1.putExtra("clubliuyan",liuyan);
                intent1.putExtra("clubimg",imgUrl);
                startActivity(intent);
                Toast.makeText(ClubDescribActivity.this, "报名成功", Toast.LENGTH_SHORT).show();

            }
        });

        baomingimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        int width = dm.widthPixels;
        int height = dm.heightPixels;

      //  Bitmap   bitmap = BitmapFactory.decodeResource(getResources(), downloadimg(imgUrl));
        bitmap=downloadimg(imgUrl);

           // Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height / 4);
            //clubImg.setImageDrawable(new BitmapDrawable(resizedBitmap));
           // clubImg.setImageDrawable(new BitmapDrawable(resizedBitmap));
            clubImg.setImageBitmap(bitmap);

       // Bitmap resizedBitmap1 = Bitmap.createScaledBitmap(bitmap , 160, 65, true);
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
                bitmap= loadedImage;
            }

        });
        return bitmap;
    }
}
