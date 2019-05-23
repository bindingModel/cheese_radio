package com.cheese.radio.ui.home.page.banner;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.binding.model.App;
import com.binding.model.adapter.AdapterType;
import com.binding.model.adapter.IEventAdapter;
import com.binding.model.adapter.recycler.RecyclerAdapter;
import com.binding.model.cycle.DataBindingFragment;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.base.view.recycle.PagerLayoutManager;
import com.cheese.radio.databinding.ItemHomePageBannerBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.home.page.HomePageParams;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


@ModelView(R.layout.item_home_page_banner)
public class HomePageBannerModel extends PagerModel<DataBindingFragment, ItemHomePageBannerBinding, HomePageBannerEntity> implements GridInflate<ItemHomePageBannerBinding> {
    private HomePageParams params = new HomePageParams("banner").setAreaSelf("宁波");

    @Inject
    HomePageBannerModel() {
        super(new ViewPagerAdapter<>());
    }

    @Inject
    RadioApi api;
    private RecyclerAdapter<HomePageBannerEntity> adapter;
    private PagerLayoutManager pagerLayoutManager;
    private Disposable carousel;

    @Override
    public void attachView(Bundle savedInstanceState, DataBindingFragment dataBindingFragment) {
        super.attachView(savedInstanceState, dataBindingFragment);
        setRotate(true);
    }

    @Override
    public int getSpanSize() {
        return 4;
    }

//    public void setArea(String area) {
//        params.setArea(area);
//        setRcHttp((offset1, refresh) -> api.getBanner(params)
//                .compose(new RestfulTransformer<>())
//                .doOnNext(it->getDataBinding().viewPager.setOffscreenPageLimit(it.size()))
//                .doOnNext(list -> ViewGroupBindingAdapter.addInflates(getDataBinding().radioGroup, ReflectUtil.copyList(list,1))));
//
////        setOffscreenPageLimit
//    }

    public void setArea(String area) {


        if (carousel != null) {
            carousel.dispose();
            carousel = null;
        }
        params.setArea(area);
        addDisposable(carousel = api.getBanner(params)
                .compose(new RestfulTransformer<>())
//                .concatMap(Observable::fromIterable)
//                .toList().toObservable()
                .doOnNext(it-> {
                    if(getDataBinding().wheelView.getLayoutManager()==null){ init(); }
                })

                .doOnNext(it -> adapter.setList(IEventAdapter.NO_POSITION, it, AdapterType.refresh))
                .flatMap(it -> Observable.interval(2, TimeUnit.SECONDS))
//                .doOnNext(it-> {
//                        if(getDataBinding().wheelView.getLayoutManager()==null){ init(); }
//                })
//                .doOnNext(it->getDataBinding().wheelView.getLayoutManager().toString())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(it ->{
                                            if(getDataBinding().wheelView.getLayoutManager()==null){ init(); }
                    getDataBinding().wheelView.smoothScrollToPosition((
                                (pagerLayoutManager.findFirstVisibleItemPosition() + 1)
                                        % adapter.size()));}, BaseUtil::toast));
    }

    private void init() {
        if (adapter == null)
            adapter = new RecyclerAdapter<>();
        pagerLayoutManager = new PagerLayoutManager(App.getCurrentActivity(), LinearLayoutManager.HORIZONTAL,false);
        pagerLayoutManager.setMILLISECONDS_PER_INCH(50F);
        getDataBinding().wheelView.setAdapter(adapter);
        getDataBinding().wheelView.setLayoutManager(pagerLayoutManager);
//            BaseUtil.toast(getDataBinding());
    }
}

