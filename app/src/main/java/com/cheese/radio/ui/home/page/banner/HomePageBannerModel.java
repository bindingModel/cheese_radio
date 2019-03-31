package com.cheese.radio.ui.home.page.banner;

import android.os.Bundle;

import com.binding.model.binding.ViewGroupBindingAdapter;
import com.binding.model.cycle.DataBindingFragment;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.util.ReflectUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ItemHomePageBannerBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.home.page.HomePageParams;

import javax.inject.Inject;


@ModelView(R.layout.item_home_page_banner)
public class HomePageBannerModel extends PagerModel<DataBindingFragment, ItemHomePageBannerBinding, HomePageBannerEntity> implements GridInflate<ItemHomePageBannerBinding> {
    private HomePageParams params = new HomePageParams("banner").setAreaSelf("宁波");

    @Inject
    HomePageBannerModel() {
        super(new ViewPagerAdapter<>());
    }

    @Inject
    RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, DataBindingFragment dataBindingFragment) {
        super.attachView(savedInstanceState, dataBindingFragment);
        setRotate(true);
        setRcHttp((offset1, refresh) -> api.getBanner(params)
                .compose(new RestfulTransformer<>())
                .doOnNext(list -> ViewGroupBindingAdapter.addInflates(getDataBinding().radioGroup, ReflectUtil.copyList(list,1))));
    }


    @Override
    public int getSpanSize() {
        return 4;
    }

    public void setArea(String area) {
        params.setArea(area);
    }
}
