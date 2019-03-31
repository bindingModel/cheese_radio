package com.cheese.radio.ui.home.circle.join;

import com.cheese.radio.inject.api.ContentParams;

/**
 * @author 29283
 * @create 2018/9/25.
 */
public class JoinCircleDetailParams extends ContentParams {
    public JoinCircleDetailParams(String activity) {
        super(activity);
    }

    public JoinCircleDetailParams() {
    }
    private long activityid;
    private String contacts;
    private String phone;

    public long getActivityid() {
        return activityid;
    }

    public void setActivityid(long activityid) {
        this.activityid = activityid;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
