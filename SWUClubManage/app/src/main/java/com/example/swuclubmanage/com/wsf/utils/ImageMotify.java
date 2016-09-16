package com.example.swuclubmanage.com.wsf.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by 王书发 on 2016/6/3.
 */
public class ImageMotify {
    private Bitmap bitmap=null;
    private Context context=null;
    private int screenWidth=0;
    private int screenHeight=0;

    public ImageMotify(Bitmap bitmap, Context context, int screenWidth,int screenHeight) {
        this.bitmap = bitmap;
        this.context = context;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public Bitmap mitifyImageSize( float scaleWidth,float scaleHeight){
        Bitmap result=null;
        Matrix matrix=new Matrix();
      scaleWidth=screenWidth*(1.0f)/(bitmap.getWidth());
        scaleHeight=(screenHeight*0.3f)/(bitmap.getHeight());
        matrix.postScale(scaleWidth, scaleHeight);
        result=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return result;
    }

}
