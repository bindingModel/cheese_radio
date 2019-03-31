package com.cheese.radio.ui.user.calendar;

import android.text.TextUtils;

import com.cheese.radio.inject.api.ContentParams;

import java.util.Calendar;

/**
 * Created by 29283 on 2018/3/31.
 */

public class ClassCalendarParams extends ContentParams {
    //    参数名	参数含义	是否必填	生成规则	样例
//    method	方法名	是	固定	getClassCalendar
//    token	用户令牌	是	用户登录或注册后获取
//    yearMonth	年月	是	yyyy-MM	2018-04
//    age	年龄段	否	第一次需要用户选择	4-5，6-7
    private String yearMonth;
    private String age;
    private Calendar now = Calendar.getInstance();
    private Integer courseTypeId;

    private transient int year,month;
    public ClassCalendarParams(String method, String yearMonth) {
        super(method);
        this.yearMonth = yearMonth;
    }

    public ClassCalendarParams(String method) {
        super(method);
    }

    public String getYearMonth() {
        if (TextUtils.isEmpty(yearMonth)) {
            return String.valueOf(now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1);
        }
        return yearMonth;
    }

    public ClassCalendarParams setYearMonth(int year, int month) {
        if (month > 12) {
            month -= 12;
            year += 1;
        } else if (month < 1) {
            month += 12;
            year -= 1;
        }
        this.year=year;
        this.month=month;
        yearMonth = String.valueOf(now.get(Calendar.YEAR)) + "-" + month;
        return this;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(int courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
