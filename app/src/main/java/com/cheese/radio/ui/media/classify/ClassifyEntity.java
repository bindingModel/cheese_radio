package com.cheese.radio.ui.media.classify;

import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.inter.SpanSize;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;

/**
 * Created by 29283 on 2018/3/17.
 */
@ModelView(R.layout.item_classify_tag)
public class ClassifyEntity extends ViewInflateRecycler implements SpanSize, GridInflate {

    /**
     * tagId : 22
     * location :
     * tagName : 奇幻冒险
     */

    private String tagId;
    private String location;
    private String tagName;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public int getSpanSize() {
        return 5;
    }

    public void onClick(View view){
        ARouterUtil.itemNavigation(location,tagId);
    }
}