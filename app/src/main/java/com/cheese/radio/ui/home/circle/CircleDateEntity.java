package com.cheese.radio.ui.home.circle;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.binding.model.Config;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.BuildConfig;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.IkeApplication;


@ModelView(R.layout.holder_circle_date)
public class CircleDateEntity extends ViewInflateRecycler implements Parcelable {
    private String img;
    private int allowSign; // 1=允许报名，0=不允许
    private int id;
    private String title;
    private String content;
    public String getBtnText(){
        return allowSign==0?"详情":"报名";
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getAllowSign() {
        return allowSign;
    }

    public void setAllowSign(int allowSign) {
        this.allowSign = allowSign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void onInfoClick(View view) {
        Bundle bundle =new Bundle();
        bundle.putInt(Constant.id,id);
        bundle.putString(Config.title,title);
        ARouterUtil.navigation(ActivityComponent.Router.activityDetail,bundle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
        dest.writeInt(this.allowSign);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    public CircleDateEntity() {
    }

    protected CircleDateEntity(Parcel in) {
        this.img = in.readString();
        this.allowSign = in.readInt();
        this.id = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<CircleDateEntity> CREATOR = new Parcelable.Creator<CircleDateEntity>() {
        @Override
        public CircleDateEntity createFromParcel(Parcel source) {
            return new CircleDateEntity(source);
        }

        @Override
        public CircleDateEntity[] newArray(int size) {
            return new CircleDateEntity[size];
        }
    };
}
