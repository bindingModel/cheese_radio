package com.cheese.radio.ui.user.my.favority;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;

/**
 * Created by 29283 on 2018/4/13.
 */
@ModelView(R.layout.head_my_favority)
public class MyFavorityTitle extends ViewInflateRecycler {
    private String title;
    private String total;
    public MyFavorityTitle(String title) {
        this.title = title;
    }

    public MyFavorityTitle(String title, String total) {
        this.title = title;
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotal() {
        return total!=null?("共"+total+"个故事"):null;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
