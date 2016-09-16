package com.example.swuclubmanage.com.wsf.activity;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.utils.DownloadImg;
import com.example.swuclubmanage.com.wsf.utils.SimpleImageLoadingListener;
import com.example.swuclubmanage.com.wsf.utils.TitleBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

public class SeetingActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_download_img);
    }

    private void initTitleBar() {
        new TitleBar(this).setTitle("设置")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

}


