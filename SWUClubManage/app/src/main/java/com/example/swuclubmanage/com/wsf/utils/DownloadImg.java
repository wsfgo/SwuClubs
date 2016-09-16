package com.example.swuclubmanage.com.wsf.utils;

import android.graphics.Bitmap;
import android.view.View;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by 王书发 on 2016/6/11.
 */
public class DownloadImg {
    public static Bitmap downloadImg(String imgUrl){
        final Bitmap[] bitmap = new Bitmap[1];
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().loadImage(imgUrl, options, new SimpleImageLoadingListener(){

            @Override
            public void onLoadingComplete(String imageUri, View view,
                                          Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                ;
                bitmap[0] =loadedImage;
            }

        });
        return bitmap[0];
    }

}
