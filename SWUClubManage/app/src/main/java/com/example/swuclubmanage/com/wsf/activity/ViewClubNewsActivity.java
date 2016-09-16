package com.example.swuclubmanage.com.wsf.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.utils.SlideShowView;
import com.example.swuclubmanage.com.wsf.utils.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class ViewClubNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_club_news);
        initTitleBar();


    }

    private void initTitleBar() {
        new TitleBar(this).setTitle("社团趣事")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}
