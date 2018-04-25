package com.cheese.radio.ui.user.calendar;

import android.text.TextUtils;

import com.cheese.radio.base.IkeParams;

import java.util.Calendar;

/**
 * Created by 29283 on 2018/3/31.
 */

public class ClassCalendarParams extends IkeParams {
//    参数名	参数含义	是否必填	生成规则	样例
//    method	方法名	是	固定	getClassCalendar
//    token	用户令牌	是	用户登录或注册后获取
//    yearMonth	年月	是	yyyy-MM	2018-04
//    age	年龄段	否	第一次需要用户选择	4-5，6-7
        private String method ;
        private String yearMonth;
        private String age;
        private Calendar now = Calendar.getInstance();
    public ClassCalendarParams(String method, String yearMonth) {
        this.method = method;
        this.yearMonth = yearMonth;
    }
    public ClassCalendarParams(String method) {
        this.method = method;
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getYearMonth() {
        if(TextUtils.isEmpty(yearMonth)){
        StringBuilder sb=new StringBuilder();
        sb.append(now.get(Calendar.YEAR)).append("-").append(now.get(Calendar.MONTH)+1);
            return sb.toString();
        }
        return yearMonth;
    }

    public void setYearMonth(int year,int month) {
        StringBuilder sb=new StringBuilder();
        sb.append(now.get(Calendar.YEAR)).append("-").append(month);
        yearMonth=sb.toString();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
