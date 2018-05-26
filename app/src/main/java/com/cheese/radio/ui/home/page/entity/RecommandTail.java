package com.cheese.radio.ui.home.page.entity;

import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;

@ModelView(R.layout.holder_home_page_recommand_tail)
public class RecommandTail extends ViewInflateRecycler {

    private String location;
    private int locationId;
    private String title;

    public RecommandTail(String location, int locationId, String title) {
        this.location = location;
        this.locationId = locationId;
        this.title = title;
    }

    public RecommandTail() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void onClick(View view) {
        ARouterUtil.itemNavigation(location, locationId, title);
    }


    @Override
    public int getSpanSize() {
        return 4;
    }
}
