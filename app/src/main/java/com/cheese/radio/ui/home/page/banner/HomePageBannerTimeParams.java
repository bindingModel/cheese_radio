package com.cheese.radio.ui.home.page.banner;

import com.cheese.radio.base.IkeParams;

public class HomePageBannerTimeParams extends IkeParams {
    private String method;

    public HomePageBannerTimeParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
