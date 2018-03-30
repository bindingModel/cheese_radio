package com.cheese.radio.ui.user.my.course;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;

/**
 * Created by 29283 on 2018/3/30.
 */
@ModelView(R.layout.holder_my_course)
public class MyCourseEntity extends ViewInflateRecycler {
    /**
     * classId : 123
     * teacherId : 12
     * teacherName : 麦麦
     * className : 课程名称
     * classImage : http://xx/xx.jpg
     * time : 18:00-20:00
     * teacherIcon : http://xx/xx.jpg
     * day : 2018-04-01
     * bookId : 1
     */

    private int classId;
    private int teacherId;
    private String teacherName;
    private String className;
    private String classImage;
    private String time;
    private String teacherIcon;
    private String day;
    private int bookId;

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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getClassTime() {
        StringBuilder builder = new StringBuilder();
        if (day != null) builder.append(day).append("  ");
        if (time != null) builder.append(time);
        return builder.toString();
    }
}

