package com.example.swuclubmanage.com.wsf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王书发 on 2016/6/15.
 */
public class GetCurrentTime {
    public static String getCurrentTime(){
        SimpleDateFormat    formatter    =   new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
        String    time    =    formatter.format(curDate);
      return time;
    }

}
