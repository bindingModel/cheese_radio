package com.cheese.radio.util.calendarutils;

import android.content.Context;

import com.cheese.radio.ui.user.calendar.CalendarEntity;

import java.util.ArrayList;
import java.util.List;

public class CalendarUtil {
    /**
     * 获得当月显示的日期（上月 + 当月 + 下月）
     * @param year  当前年份
     * @param month 当前月份
     * @return
     */
    public static List<Day> getDays(int year, int month,int[] selectDay,List<CalendarEntity> tipsDays) {
        List<Day> days = new ArrayList<>();
        int week = SolarUtil.getFirstWeekOfMonth(year, month - 1);

        int lastYear;
        int lastMonth;
        if (month == 1) {
            lastMonth = 12;
            lastYear = year - 1;
        } else {
            lastMonth = month - 1;
            lastYear = year;
        }
        int lastMonthDays = SolarUtil.getMonthDays(lastYear, lastMonth);//上个月总天数

        int currentMonthDays = SolarUtil.getMonthDays(year, month);//当前月总天数

        int nextYear;
        int nextMonth;
        if (month == 12) {
            nextMonth = 1;
            nextYear = year + 1;
        } else {
            nextMonth = month + 1;
            nextYear = year;
        }
        for (int i = 0; i < week; i++) {
            days.add(initDay(selectDay,tipsDays,lastYear, lastMonth, lastMonthDays - week + 1 + i, 0));
        }

        for (int i = 0; i < currentMonthDays; i++) {
            days.add(initDay(selectDay,tipsDays,year, month, i + 1, 1));
        }

        for (int i = 0; i < 7 * getMonthRows(year, month) - currentMonthDays - week; i++) {
            days.add(initDay(selectDay,tipsDays,nextYear, nextMonth, i + 1, 2));
        }

        return days;
    }

    private static Day initDay(int[] selectDay,List<CalendarEntity> tipsDays,int year, int month, int day, int type) {
        Day theDay = new Day();
        theDay.setSolar(year, month, day);

        String[] temp = LunarUtil.solarToLunar(year, month, day);

        theDay.setLunar(new String[]{temp[0], temp[1]});
        theDay.setType(type);
        theDay.setTerm(LunarUtil.getTermString(year, month - 1, day));
        theDay.setLunarHoliday(temp[2]);

        if (type == 0) {
            theDay.setSolarHoliday(SolarUtil.getSolarHoliday(year, month, day - 1));
        } else {
            theDay.setSolarHoliday(SolarUtil.getSolarHoliday(year, month, day));
        }
        if(selectDay!=null&&selectDay.length>=3&&selectDay[0]==year&&selectDay[1]==month&&selectDay[2]==day){
            theDay.setChoose(true);
        }

        if (tipsDays!=null){
            for (int i=0;i<tipsDays.size();i++){
                CalendarEntity tipsDay=tipsDays.get(i);
                if (tipsDay!=null){
                    int[] theDay1 =tipsDay.getDays();
                    if (theDay1!=null&&theDay1.length>=3&&theDay1[0]==year&&theDay1[1]==month&&theDay1[2]==day){
                        if (tipsDay.isSelect()) {
                            theDay.setTipsType(1);
                        }else{
                            theDay.setTipsType(2);
                        }
                        break;
                    }
                }

            }
        }

        return theDay;
    }
//    public static List<Day> getDays(int year, int month,int[] selectDay,List<TipsDay> tipsDays) {
//        List<Day> days = new ArrayList<>();
//        int week = SolarUtil.getFirstWeekOfMonth(year, month - 1);
//
//        int lastYear;
//        int lastMonth;
//        if (month == 1) {
//            lastMonth = 12;
//            lastYear = year - 1;
//        } else {
//            lastMonth = month - 1;
//            lastYear = year;
//        }
//        int lastMonthDays = SolarUtil.getMonthDays(lastYear, lastMonth);//上个月总天数
//
//        int currentMonthDays = SolarUtil.getMonthDays(year, month);//当前月总天数
//
//        int nextYear;
//        int nextMonth;
//        if (month == 12) {
//            nextMonth = 1;
//            nextYear = year + 1;
//        } else {
//            nextMonth = month + 1;
//            nextYear = year;
//        }
//        for (int i = 0; i < week; i++) {
//            days.add(initDay(selectDay,tipsDays,lastYear, lastMonth, lastMonthDays - week + 1 + i, 0));
//        }
//
//        for (int i = 0; i < currentMonthDays; i++) {
//            days.add(initDay(selectDay,tipsDays,year, month, i + 1, 1));
//        }
//
//        for (int i = 0; i < 7 * getMonthRows(year, month) - currentMonthDays - week; i++) {
//            days.add(initDay(selectDay,tipsDays,nextYear, nextMonth, i + 1, 2));
//        }
//
//        return days;
//    }
//
//    private static Day initDay(int[] selectDay,List<TipsDay> tipsDays,int year, int month, int day, int type) {
//        Day theDay = new Day();
//        theDay.setSolar(year, month, day);
//
//        String[] temp = LunarUtil.solarToLunar(year, month, day);
//
//        theDay.setLunar(new String[]{temp[0], temp[1]});
//        theDay.setType(type);
//        theDay.setTerm(LunarUtil.getTermString(year, month - 1, day));
//        theDay.setLunarHoliday(temp[2]);
//
//        if (type == 0) {
//            theDay.setSolarHoliday(SolarUtil.getSolarHoliday(year, month, day - 1));
//        } else {
//            theDay.setSolarHoliday(SolarUtil.getSolarHoliday(year, month, day));
//        }
//        if(selectDay!=null&&selectDay.length>=3&&selectDay[0]==year&&selectDay[1]==month&&selectDay[2]==day){
//            theDay.setChoose(true);
//        }
//
//        if (tipsDays!=null){
//            for (int i=0;i<tipsDays.size();i++){
//                TipsDay tipsDay=tipsDays.get(i);
//                if (tipsDay!=null){
//                    int[] theDay1 =tipsDay.getDay();
//                    if (theDay1!=null&&theDay1.length>=3&&theDay1[0]==year&&theDay1[1]==month&&theDay1[2]==day){
//                        if (tipsDay.isSelect()) {
//                            theDay.setTipsType(1);
//                        }else{
//                            theDay.setTipsType(2);
//                        }
//                        break;
//                    }
//                }
//
//            }
//        }
//
//        return theDay;
//    }

    /**
     * 计算当前月需要显示几行
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthRows(int year, int month) {
        int items = SolarUtil.getFirstWeekOfMonth(year, month - 1) + SolarUtil.getMonthDays(year, month);
        int rows = items % 7 == 0 ? items / 7 : (items / 7) + 1;
        if (rows == 4) {
            rows = 5;
        }
        return rows;
    }

    /**
     * 根据ViewPager position 得到对应年月
     *
     * @param position
     * @return
     */
    public static int[] positionToDay(int position, int startY, int startM) {
        int year = position / 12 + startY;
        int month = position % 12 + startM;

        if (month > 12) {
            month = month % 12;
            year = year + 1;
        }
        return new int[]{year, month};
    }

    /**
     * 根据年月得到ViewPager position
     */
    public static int dayToPosition(int year, int month, int startY, int startM) {
        return (year - startY) * 12 + month - startM;
    }


    public static int getPxSize(Context context, int size) {
        return size * context.getResources().getDisplayMetrics().densityDpi;
    }

    public static int getTextSize(Context context, int size) {
        return (int) (size * context.getResources().getDisplayMetrics().scaledDensity);
    }
}
