package com.cheese.radio.ui.user.my.work;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;

/**
 * Created by 29283 on 2018/4/10.
 */
@ModelView(R.layout.holder_my_work)
public class MyWorkEntity extends ViewInflateRecycler {

    private String title;
    private String subTitle;
    private String playCount;
    private String location;
    private String image;
    private Integer id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
