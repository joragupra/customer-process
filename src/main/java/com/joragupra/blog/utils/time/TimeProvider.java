package com.joragupra.blog.utils.time;

import java.util.Calendar;
import java.util.Date;

public final class TimeProvider {

    static long offset = 0;

    private TimeProvider() {
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis() + offset;
    }

    public static Date now() {
        return new Date(currentTimeMillis());
    }

    public static Calendar calendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis());
        return calendar;
    }

}

