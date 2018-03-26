package com.cheese.radio.util.calendarutils;

import android.widget.TextView;

import java.util.List;

/**
 * Created by xihuan22 on 2017/7/17.
 */
public class Month {
    private int year;

    private int month;

    private boolean isSelect;

    private TextView textView;//该view之外的导航栏的textView

    private List<Day> days;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Month{" +
                "year=" + year +
                ", month=" + month +
                ", isSelect=" + isSelect +
                ", textView=" + textView +
                ", days=" + days +
                '}';
    }
}