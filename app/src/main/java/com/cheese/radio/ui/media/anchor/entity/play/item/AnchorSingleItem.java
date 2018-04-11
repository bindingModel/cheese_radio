package com.cheese.radio.ui.media.anchor.entity.play.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;

/**
 * Created by 29283 on 2018/3/16.
 */

@ModelView(R.layout.item_anchor_single)
public class AnchorSingleItem extends ViewInflateRecycler implements Parcelable {
    /**
     * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c10/c8/1622425615656.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024853%3B1522118858%26q-key-time%3D1521024853%3B1522118858%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Dec060efc36502c1c88f514275bcd29d7cfdfa5b0
     * playCount : 110
     * subTitle : 老鼠偷米，快来抓
     * id : 3
     * title : 老鼠偷米
     */

    private String image;
    private int playCount;
    private String subTitle;
    private int id;
    private String title;


    private int seconds;
    private String anchorIcon;
    private String location;
    private String anchorName;
    private String url;
    private String anchorBrief;

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getAnchorIcon() {
        return anchorIcon;
    }

    public void setAnchorIcon(String anchorIcon) {
        this.anchorIcon = anchorIcon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnchorName() {
        return anchorName;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAnchorBrief() {
        return anchorBrief;
    }

    public void setAnchorBrief(String anchorBrief) {
        this.anchorBrief = anchorBrief;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPlayCount() {
        return playCount;
    }

    public String getPlayCountString() {
        return String.valueOf(playCount);
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public void onPlayClick(View view) {
        ARouterUtil.itemNavigation("play", id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeInt(playCount);
        dest.writeString(subTitle);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(seconds);
        dest.writeString(anchorIcon);
        dest.writeString(location);
        dest.writeString(anchorName);
        dest.writeString(url);
        dest.writeString(anchorBrief);
    }

    protected AnchorSingleItem(Parcel in) {
        this.image = in.readString();
        this.playCount = in.readInt();
        this.subTitle = in.readString();
        this.id = in.readInt();
        this.title = in.readString();
        this.seconds = in.readInt();
        this.anchorIcon = in.readString();
        this.location = in.readString();
        this.anchorName = in.readString();
        this.url = in.readString();
        this.anchorBrief = in.readString();
    }

    public static final Creator<AnchorSingleItem> CREATOR = new Creator<AnchorSingleItem>() {
        @Override
        public AnchorSingleItem createFromParcel(Parcel source) {
            return new AnchorSingleItem(source);
        }

        @Override
        public AnchorSingleItem[] newArray(int size) {
            return new AnchorSingleItem[size];
        }
    };

    public int getRadius(){
        return 15;
    }
}
