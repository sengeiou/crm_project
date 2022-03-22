package com.javasm.commons.utils;

import cn.hutool.core.date.DateTime;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Component
public class DateUtils {

    private static Date frontTime;
    private static Date afterTime;


    public Date getFrontTime() {
        return frontTime;
    }

    public void setFrontTime(Date frontTime) {
        this.frontTime = frontTime;
    }

    public Date getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(Date afterTime) {
        this.afterTime = afterTime;
    }

    public static Date getDate(String date){
        //传进来时间字符串
        String dateSub = date.substring(0, 19);
        String[] dateArray = dateSub.split("T");
        String date2 ="";

        for (String t : dateArray) {
            date2 += t+" ";
        }
        String substring1 = date2.substring(0,date2.length()-1);//正确的时间格式;

        Date parse = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
             parse = dateFormat.parse(substring1);
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
             String format = simpleDateFormat.format(parse);
            parse = simpleDateFormat.parse(format);


            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

}
