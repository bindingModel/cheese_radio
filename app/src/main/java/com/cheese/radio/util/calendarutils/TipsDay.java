package com.cheese.radio.util.calendarutils;

import java.util.Arrays;

/**
 * Created by xihuan22 on 2017/7/24.
 */

public class TipsDay {
    private String dayString;
    private boolean select;
    private int[] day;

    public String getDayString() {
        return dayString;
    }

    public void setDayString(String dayString) {
        this.dayString = dayString;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int[] getDay() {
        return day;
    }

    public void setDay(int[] day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "TipsDay{" +
                "dayString='" + dayString + '\'' +
                ", select=" + select +
                ", day=" + Arrays.toString(day) +
                '}';
    }
}
