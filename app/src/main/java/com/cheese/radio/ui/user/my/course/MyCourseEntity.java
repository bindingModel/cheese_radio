package com.cheese.radio.ui.user.my.course;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.util.BaseUtil;
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
    /**
     * code : T001
     * surplus : 0
     * name : 一元体验课程
     * id : 1
     * complete : 2
     */

    private String code;
    private int surplus;
    private String name;
    private int id;
    private int complete;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

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
        return TextUtils.isEmpty(className)?name:className;
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
    public String getClassCount(){
        return "已上："+complete+" 剩余："+surplus;
    }
    SpannableStringBuilder touchText;
    public SpannableStringBuilder getTouchText() {
        if (touchText == null) {
            String msg =getClassCount();
            touchText = new SpannableStringBuilder();
            touchText.append(getClassCount());
            ClickableSpan createClick = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    onCheckInfoClick(view);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(ds.linkColor);
                    ds.setUnderlineText(false);
                }
            };
            String touchContent=String.valueOf(complete);
            int index = msg.indexOf(touchContent);
            touchText.setSpan(createClick, index,index+touchContent.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return touchText;
    }

    private void onCheckInfoClick(View view) {
        BaseUtil.toast("跳转到哪里");
    }

}


