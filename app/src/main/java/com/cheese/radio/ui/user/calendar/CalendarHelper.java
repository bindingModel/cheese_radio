package com.cheese.radio.ui.user.calendar;

import android.util.SparseArray;

import com.haibin.calendarview.Calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @name cheese_radio
 * @anthor bangbang QQ:740090077
 * @time 2018/11/20 8:50 PM
 * 只有编译器可能不骗你。
 */
public class CalendarHelper {
    private final static HashMap<String, Calendar> calendarHashMap = new HashMap<>();

    public static HashMap<String, Calendar> createCalendarMap(List<CalendarEntity> list) {
        calendarHashMap.clear();
        for (int i = 0; i < list.size(); i++) {
            int[] day = list.get(i).getDays();
            Calendar calendar = new Calendar();
            calendar.setYear(day[0]);
            calendar.setMonth(day[1]);
            calendar.setDay(day[2]);
            if (calendarHashMap.containsKey(calendar.toString())) {
                calendar = calendarHashMap.get(calendar.toString());
            }
            if (calendar != null) {
                Calendar.Scheme scheme = new Calendar.Scheme();
                scheme.setType(list.get(i).getType());
                calendar.addScheme(scheme);
                calendarHashMap.put(calendar.toString(), calendar);
            }
        }
        return calendarHashMap;
    }

    public static HashMap<String, Calendar> createCalendarMap(SparseArray<List<CalendarEntity>> sparseArray) {
        List<CalendarEntity> list = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            if (sparseArray.valueAt(i) != null) list.addAll(sparseArray.valueAt(i));
        }
        return createCalendarMap(list);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
//        calendar.addScheme(scheme);
        return calendar;
    }
}
