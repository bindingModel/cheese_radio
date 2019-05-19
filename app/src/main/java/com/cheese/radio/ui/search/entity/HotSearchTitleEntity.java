package com.cheese.radio.ui.search.entity;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.inter.SpanSize;
import com.cheese.radio.R;

/**
 * Created by 29283 on 2018/3/22.
 */
@ModelView(R.layout.item_hot_search_title)
public class HotSearchTitleEntity extends ViewInflateRecycler implements SpanSize, GridInflate {

    private String title;

    public HotSearchTitleEntity(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getSpanSize() {
       return 20;
    }
}
