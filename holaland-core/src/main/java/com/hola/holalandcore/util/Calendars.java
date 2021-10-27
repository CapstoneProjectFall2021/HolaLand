package com.hola.holalandcore.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Calendars {

    public static long getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis() / 1000;
    }

    public static String formatTime(long millisecond) {
        SimpleDateFormat isoFormat = new SimpleDateFormat("HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        String time = isoFormat.format(millisecond * 1000);
        return time;
    }

    public static String formatDate(long millisecond) {
        SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy");
        isoFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        String date = isoFormat.format(millisecond * 1000);
        return date;
    }

    public static Timestamp dateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }
}
