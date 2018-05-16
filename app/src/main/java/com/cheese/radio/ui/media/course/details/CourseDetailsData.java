package com.cheese.radio.ui.media.course.details;

import com.binding.model.util.BaseUtil;

import static com.binding.model.util.BaseUtil.T;

/**
 * Created by 29283 on 2018/4/1.
 */

public class CourseDetailsData {

        /**
         * classId : 109
         * teacherId : 2
         * teacherName : 主播2
         * className : 如何让孩子学会主动沟通
         * classImage : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c8/c3/1626b2e81d339.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1522216633%3B3100139833%26q-key-time%3D1522216633%3B3100139833%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D5a8d9beaca5d94c7f7a2affe6c30648fa0b5ce8a
         * startTime : 10:00-11:00
         * time : 60
         * teacherIcon : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c21/c5/1626b21444714.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1522215766%3B3100138966%26q-key-time%3D1522215766%3B3100138966%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Dc764403fed8cbe1128e55ebf016afabb03fdbe14
         * totalCount : 20
         * classDesc :
         * day : 2018-03-31
         * leftCount : 15
         */

        private int classId;
        private int teacherId;
        private String teacherName;
        private String className;
        private String classImage;
        private String startTime;
        private int time;
        private String teacherIcon;
        private int totalCount;
        private String classDesc;
        private String day;
        private int leftCount;

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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getTeacherIcon() {
            return teacherIcon;
        }

        public void setTeacherIcon(String teacherIcon) {
            this.teacherIcon = teacherIcon;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public String getClassDesc() {
            return classDesc;
        }

        public void setClassDesc(String classDesc) {
            this.classDesc = classDesc;
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

        public String getTimeEntity(){
            StringBuilder builder=new StringBuilder();
            String[] strings=startTime.split("-");
            builder.append(day).append("\t").append(strings[0]).append("开始上课");
            return builder.toString();
        }
    public CharSequence getCountMsg() {

        return BaseUtil.colorText(
                T(String.valueOf(totalCount-leftCount), false, "FFB100", 1),
                T("/"+String.valueOf(totalCount), true, "BDBDBD"),
                T("已经预约人数", false, "BDBDBD")
        );
    }
    public CharSequence getKeepTimeMsg() {

        return BaseUtil.colorText(
                T(String.valueOf(time), true, "FFB100", 1),
                T("课程时长/分", false, "BDBDBD")
        );
    }
}
