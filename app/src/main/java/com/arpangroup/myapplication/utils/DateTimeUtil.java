package com.arpangroup.myapplication.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static final DateFormat dateFormat_hh_mm_a = new SimpleDateFormat("hh:mm a");


    public static String getTimeInhh_mm_aFormat(Long timestamp){
        if(timestamp == null) timestamp = new Date().getTime();
        try{
            return dateFormat_hh_mm_a.format(timestamp);
        }catch (Exception e){
            try {
                return dateFormat_hh_mm_a.format(new Date().getTime());
            }catch (Exception e1){

            }
        }
        return "";
    }
}
