package com.skuniv.bigdata.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
    public static String createCurrent(){
        Date date = new Date();
        return formatter.format(date);
    }
}
