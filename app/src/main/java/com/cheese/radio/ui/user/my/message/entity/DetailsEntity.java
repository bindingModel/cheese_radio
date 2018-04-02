package com.cheese.radio.ui.user.my.message.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.Event;
import com.binding.model.model.inter.Inflate;
import com.cheese.radio.R;

import java.util.zip.Inflater;

/**
 * Created by 29283 on 2018/3/31.
 */
@ModelView(R.layout.holder_message_details)
public class DetailsEntity extends ViewInflateRecycler implements Parcelable {
    /**
     * createTime : 2018-03-28 21:54:12
     * locationId : null
     * isRead : false
     * location : null
     * id : 3
     * title : 通知测试
     * content : 这是一条系统通知测试
     */

    private String createTime;
    private String locationId;
    private boolean isRead;
    private String location;
    private int id;
    private String title;
    private String content;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
       dest.writeString(createTime);
        dest.writeString(locationId);
        dest.writeByte((byte)(isRead ?1:0));//if isRead == true, byte == 1
        dest.writeString(location);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(content);
    }
    protected DetailsEntity(Parcel in){
        this.createTime=in.readString();
        this.locationId=in.readString();
        this.isRead =in.readByte()!=0;   //myBoolean == true if byte != 0
        this.locationId=in.readString();
        this.id=in.readInt();
        this.title=in.readString();
        this.content=in.readString();
    }
    public static final Creator<DetailsEntity> CREATOR = new Creator<DetailsEntity>() {
        @Override
        public DetailsEntity createFromParcel(Parcel source) {
            return new DetailsEntity(source);
        }

        @Override
        public DetailsEntity[] newArray(int size) {
            return new DetailsEntity[size];
        }
    };

    public void onClick(View view){
        Event.event(R.id.DetailsModel,this,view);
    }
}
