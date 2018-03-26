package com.cheese.radio.util.calendarutils;


import java.util.Arrays;

/**
 * Created by xihuan22 on 2017/7/17.
 */
public class Day {
    private int[] solar;//阳历年、月、日
    private String[] lunar;//农历月、日
    private String solarHoliday;//阳历节假日
    private String lunarHoliday;//农历节假日
    private int type;//0:上月,1:当月,2:下月
    private String term;//节气
    private boolean isChoose;//选中状态
    private int tipsType;//提示类型

    public int[] getSolar() {
        return solar;
    }

    public void setSolar(int year, int month, int day) {
        this.solar = new int[]{year, month, day};
    }

    public String[] getLunar() {
        return lunar;
    }

    public void setLunar(String[] lunar) {
        this.lunar = lunar;
    }

    public String getSolarHoliday() {
        return solarHoliday;
    }

    public void setSolarHoliday(String solarHoliday) {
        this.solarHoliday = solarHoliday;
    }

    public String getLunarHoliday() {
        return lunarHoliday;
    }

    public void setLunarHoliday(String lunarHoliday) {
        this.lunarHoliday = lunarHoliday;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public int getTipsType() {
        return tipsType;
    }

    public void setTipsType(int tipsType) {
        this.tipsType = tipsType;
    }

    @Override
    public String toString() {
        return "Day{" +
                "solar=" + Arrays.toString(solar) +
                ", lunar=" + Arrays.toString(lunar) +
                ", solarHoliday='" + solarHoliday + '\'' +
                ", lunarHoliday='" + lunarHoliday + '\'' +
                ", type=" + type +
                ", term='" + term + '\'' +
                ", isChoose=" + isChoose +
                ", tipsType=" + tipsType +
                '}';
    }
}