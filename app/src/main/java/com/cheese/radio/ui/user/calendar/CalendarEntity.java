package com.cheese.radio.ui.user.calendar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.util.calendarutils.TipsDay;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by 29283 on 2018/3/26.
 */
@ModelView(R.layout.item_calendar_class)
public class CalendarEntity extends ViewInflateRecycler {
//图还没修。

    /**
     * classId : 1
     * teacherId : 2
     * teacherName : 主播2
     * className : 如何帮孩子克服恐惧心理
     * classImage : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c26/c20/1626b2edb9b3c.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1522216656%3B3100139856%26q-key-time%3D1522216656%3B3100139856%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D752df37f4947e27e2a347421b23d9ffa45ced848
     * time : 18:00-19:00
     * teacherIcon : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c21/c5/1626b21444714.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1522215766%3B3100138966%26q-key-time%3D1522215766%3B3100138966%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Dc764403fed8cbe1128e55ebf016afabb03fdbe14
     * day : 2018-03-01
     * leftCount : 26
     */

    private int classId;
    private int teacherId;
    private String teacherName;
    private String className;
    private String classImage;
    private String time;
    private String teacherIcon;
    private String day;

    private int leftCount; //  剩余数量
    private String bookId;////  预约编号（已经预约成功的有效）

    private int[] days;//数组形式的年月日

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassImage() {
        return classImage;
    }

    public void setClassImage(String classImage) {
        this.classImage = classImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacherIcon() {
        return teacherIcon;
    }

    public void setTeacherIcon(String teacherIcon) {
        this.teacherIcon = teacherIcon;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int[] getDays() {
        if (days == null)
            return days = convertDay(day);
        else return days;
    }

    public void setDays(int[] days) {
        this.days = days;
    }

    public boolean isBook() {
        //满人，未报上返回flase，只要报上就返回true
        return bookId != null;
    }
    public boolean isCanBook(){
        return leftCount!=0;
    }

    private int[] convertDay(String string) {
        int[] res = null;
        if (string == null) {
            return res;
        }
        try {
            String[] strings = string.split("-");
            if (strings.length >= 2) {
                res = new int[strings.length];
                for (int i = 0; i < strings.length; i++) {
                    res[i] = Integer.valueOf(strings[i]);
                }
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Drawable getEnrollBackground() {

        if (bookId != null) return App.getDrawable(R.drawable.calendarview_rectangle_green);
        if (leftCount == 0) return App.getDrawable(R.drawable.calendarview_rectangle_gray);
        return App.getDrawable(R.drawable.calendarview_rectangle_yellow);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEntity entity = (CalendarEntity) o;

        if (classId != entity.classId) return false;
        if (teacherId != entity.teacherId) return false;
        if (teacherName != null ? !teacherName.equals(entity.teacherName) : entity.teacherName != null)
            return false;
        if (className != null ? !className.equals(entity.className) : entity.className != null)
            return false;
        if (classImage != null ? !classImage.equals(entity.classImage) : entity.classImage != null)
            return false;
        if (time != null ? !time.equals(entity.time) : entity.time != null) return false;
        if (teacherIcon != null ? !teacherIcon.equals(entity.teacherIcon) : entity.teacherIcon != null)
            return false;
        if (day != null ? !day.equals(entity.day) : entity.day != null) return false;
        return Arrays.equals(days, entity.days);
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + teacherId;
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (classImage != null ? classImage.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (teacherIcon != null ? teacherIcon.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(days);
        return result;
    }

    public String getEnrolMsg() {

        if (bookId != null) return "已预约";
        if (leftCount == 0) return "已约满";
        return "预约";

    }

    public void onCourseClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.classId, classId);
        ARouterUtil.navigation(ActivityComponent.Router.coursedetails, bundle);
    }

    public void onTeacherClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.authorId, teacherId);
        ARouterUtil.navigation(ActivityComponent.Router.author, bundle);
    }

    public void onEnrollClick(View view) {
        if (bookId != null) return;//"已预约";
        if (leftCount == 0) return;//"已约满";
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.classId, classId);
        ARouterUtil.navigation(ActivityComponent.Router.coursedetails, bundle);
    }

}
