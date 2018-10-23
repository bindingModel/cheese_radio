package com.cheese.radio.ui.user.my.course;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import com.binding.model.App;
import com.binding.model.Config;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;

import static com.cheese.radio.inject.component.ActivityComponent.Router.course;

/**
 * Created by 29283 on 2018/3/30.
 */
@ModelView(value = {
        R.layout.holder_my_course_type,
        R.layout.holder_my_course
})
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

    @Override
    public int getModelIndex() {
        return TextUtils.isEmpty(className)?0:1;
    }

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
    private int appointing;
    /**
     * classId : 920
     * teacherId : 17
     * teacherName : 蔡瑜
     * courseState : 预约中
     * startTime : 20:00-21:00
     * className : 课程2
     * classImage : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c18/c18/1665276c36724.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1538981827%3B3116905027%26q-key-time%3D1538981827%3B3116905027%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D2044c46637a2885eaad296d54a44798afdd28cd5
     * time : 20:00-21:00
     * teacherIcon : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c9/c14/1631f003d1e27.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1525233500%3B3103156700%26q-key-time%3D1525233500%3B3103156700%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D6c0876391cc038a4da82f3bb561e729249c061d1
     * day : 2018-10-18
     * bookId : 47
     */
    private String courseState;
    private String startTime;

    public int getAppointing() {
        return appointing;
    }

    public void setAppointing(int appointing) {
        this.appointing = appointing;
    }

    public String getCourseState() {
        return courseState;
    }

    public void setCourseState(String courseState) {
        this.courseState = courseState;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
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
        return "已上课时： "+complete;
    }
    SpannableStringBuilder finishText;
    public SpannableStringBuilder getFinishText() {
        if (finishText == null) {
            String msg =getClassCount();
            finishText = new SpannableStringBuilder();
            finishText.append(getClassCount());
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
            finishText.setSpan(createClick, index,index+touchContent.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return finishText;
    }
    private SpannableStringBuilder bookText;
    public SpannableStringBuilder getBookText() {
       String content =  "已预约课时： "+appointing;
        if (bookText == null) {
            bookText = new SpannableStringBuilder();
            bookText.append(content);
            ClickableSpan createClick = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    onCheckInfoClick(view);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(App.getColor(R.color.vk_share_blue_color));
                    ds.setUnderlineText(false);
                }
            };
            String touchContent=String.valueOf(appointing);
            int index = content.indexOf(touchContent);
            bookText.setSpan(createClick, index,index+touchContent.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return bookText;
    }
    public String getSurplusText(){
        return "剩余课时： "+surplus;
    }

    public void onCheckInfoClick(View view) {
//        BaseUtil.toast("跳转到哪里");
        Bundle bundle=new Bundle();
        bundle.putString(Config.title,view.getContext().getString(R.string.my_course)+" - "+name);
        bundle.putInt(Constant.courseTypeId,id);
        ARouterUtil.navigation(course,bundle);
    }
    //以下部分为 holder_my_course的引用
        public Drawable getCourseStateBg(){
            switch (courseState){
                case "预约中" :return App.getDrawable(R.drawable.my_class_type_book_bg);
                default:return App.getDrawable(R.drawable.my_class_type_finish_bg);
            }
        }
    /**
     * 查看主播详情
     */
    public void onAnchorClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.authorId, this.getTeacherId());
        ARouterUtil.navigation(ActivityComponent.Router.author, bundle);
    }
}


