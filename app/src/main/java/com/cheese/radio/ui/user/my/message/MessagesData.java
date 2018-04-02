package com.cheese.radio.ui.user.my.message;

import android.graphics.drawable.Drawable;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.ui.user.my.message.entity.DetailsEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29283 on 2018/3/31.
 */
public class MessagesData {

    private ArrayList<DetailsEntity> system;
    private ArrayList<DetailsEntity> book;
    private ArrayList<DetailsEntity> user;
    @SerializedName("class")
    private ArrayList<DetailsEntity> classX;
    private Integer index;

    public ArrayList<DetailsEntity> getSystem() {
        return system;
    }

    public void setSystem(ArrayList<DetailsEntity> system) {
        this.system = system;
    }

    public ArrayList<DetailsEntity> getBook() {
        return book;
    }

    public void setBook(ArrayList<DetailsEntity> book) {
        this.book = book;
    }

    public ArrayList<DetailsEntity> getUser() {
        return user;
    }

    public void setUser(ArrayList<DetailsEntity> user) {
        this.user = user;
    }

    public ArrayList<DetailsEntity> getClassX() {
        return classX;
    }

    public void setClassX(ArrayList<DetailsEntity> classX) {
        this.classX = classX;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
   /* public Drawable getThumbImage() {
        switch (index) {
            case 1:
                return App.getDrawable(R.mipmap.system_msg);
            case 2:
                return App.getDrawable(R.mipmap.vip_msg);
            case 3:
                return App.getDrawable(R.mipmap.class_msg);
            case 4:
                return App.getDrawable(R.mipmap.book_msg);
            default:
                return App.getDrawable(R.mipmap.book_msg);
        }
    }*/

   /* public String getTitle() {
        switch (index) {
            case 1:
                return "系统通知";
            case 2:
                return "会员通知";
            case 3:
                return "上课通知";
            case 4:
                return "预约通知";
            default:
                return "预约通知";
        }
    }*/

  /*  public String getMsg() {
        switch (index) {
            case 1:
                return system.get(0).getContent();
            case 2:
                return book.get(0).getContent();
            case 3:
                return user.get(0).getContent();
            case 4:
                return classX.get(0).getContent();
            default:
                return classX.get(0).getContent();
        }
    }*/
}
