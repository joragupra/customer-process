package com.joragupra.blog.utils.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeMachine {

    public static void reset() {
        TimeProvider.offset = 0;
    }

    public static void fastForward(long offset) {
        TimeProvider.offset += offset;
    }

    public static void rewind(long offset) {
        TimeProvider.offset -= offset;
    }

    public static void goTo(LocalDateTime localDateTime) {
        goTo(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public static void goTo(Date date) {

        goTo(date.getTime());

    }

    public static void goTo(long timestamp) {

        long currentTimestamp = TimeProvider.currentTimeMillis();

        if (currentTimestamp > timestamp) {

            TimeProvider.offset -= currentTimestamp - timestamp;

        } else {

            TimeProvider.offset += timestamp - currentTimestamp;

        }

    }

}
