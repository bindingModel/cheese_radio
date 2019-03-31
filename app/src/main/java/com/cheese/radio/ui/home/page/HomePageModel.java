package com.cheese.radio.ui.home.page;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.request.RecyclerStatus;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.InfoEntity;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.base.rxjava.RestfulZipTransformer;
import com.cheese.radio.databinding.FragmentHomePageBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.CheeseApplication;
import com.cheese.radio.ui.home.page.banner.HomePageBannerModel;
import com.cheese.radio.ui.home.page.entity.CategoryEntity;
import com.cheese.radio.ui.home.page.entity.RecommandEntity;
import com.cheese.radio.ui.home.page.entity.RecommandTail;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.ui.user.my.push.NewMessageCountParams;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.cheese.radio.inject.component.ActivityComponent.Router.search;

/**
 * Created by 29283 on 2018/3/3.
 */
@ModelView(value = R.layout.fragment_home_page, model = true)
public class HomePageModel extends RecyclerModel<HomePageFragment, FragmentHomePageBinding, GridInflate> {
    @Inject
    HomePageModel() {
    }

    @Inject
    RadioApi api;
    public ObservableField<String> redTipCount = new ObservableField<>("0");
    public ObservableBoolean redTipBoolean = new ObservableBoolean(false);
    public String[] names = {"宁波", "杭州", "重庆"};
    @Inject HomePageBannerModel model;

    @Override
    public void attachView(Bundle savedInstanceState, HomePageFragment homePageFragment) {
        super.attachView(savedInstanceState, homePageFragment);
        getDataBinding().layoutRecycler.setVm(this);
        GridLayoutManager layoutManager = new GridLayoutManager(homePageFragment.getContext(), 4);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
        initLocation(getDataBinding().spinner);
        setEnable(true);
        setPageFlag(false);
        upDataMsg();
        setRoHttp((offset1, refresh) -> getZip());
        model.attachContainer(getT(), (ViewGroup) getDataBinding().getRoot(), false, null);
//        model.setArea(names[0]);

    }

    private void initLocation(Spinner spinner) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model.setArea(names[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void upDataMsg() {
        if (CheeseApplication.isLogin(false))
            addDisposable(api.getNewMessageCount(new NewMessageCountParams("newMessageCount"))
                    .compose(new RestfulTransformer<>())
                    .subscribe(newMessageCountData -> {
                        redTipCount.set(String.valueOf(newMessageCountData.getCount()));
                        if (newMessageCountData.getCount() != null && newMessageCountData.getCount() != 0)
                            redTipBoolean.set(true);
                        else redTipBoolean.set(false);
                    }, BaseUtil::toast));
    }

    private Observable<List<GridInflate>> getZip() {
        Observable<InfoEntity<List<CategoryEntity>>> categoriy = api.getCategoriy(new HomePageParams("category")).compose(new RestfulZipTransformer<>());
        Observable<InfoEntity<List<RecommanData>>> recommandList = api.getRecommand(new HomePageParams("recommandList")).compose(new RestfulZipTransformer<>());
        return Observable.zip(categoriy, recommandList, (cate, entity) -> {
            List<GridInflate> list = new ArrayList<>();
            if (cate.code() == 0 && cate.getData() != null && !cate.getData().isEmpty()) {
                list.addAll(cate.getData().subList(0, 4));
            }
            list.add(model);
            if (entity.code() == 0 && entity.getData() != null && !entity.getData().isEmpty()) {
                for (RecommanData data : entity.getData()) {
                    list.add(data);
                    if (data.getViewType().equals("list"))
                        for (RecommandEntity recommandEntity : data.getList()) {
                            recommandEntity.setIndex(1);
                        }

                    list.addAll(data.getList());
                    if (data.getIslocation())
                        list.add(new RecommandTail(data.getLocation(), data.getLocationId(), data.getTitle()));
                }
                if (AudioServiceUtil.getInstance().getId() == 0)
                    AudioServiceUtil.getInstance().setId(entity.getData().get(0).getList().get(0).getId());
            }
            return list;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void onSearchClick(View view) {
        ARouterUtil.navigation(search);
    }

    public void onMessageClick(View view) {
        if (CheeseApplication.isLogin(true))
            ARouterUtil.navigation(ActivityComponent.Router.message);
        else {
            finish();
            BaseUtil.toast("请登陆后再试");
        }

    }

    public void refreshPlayRecord(Integer playId) {
        for (GridInflate gridInflate : getAdapter().getList()) {
            if (gridInflate instanceof RecommandEntity) {
                RecommandEntity entity = ((RecommandEntity) gridInflate);
                if (entity.getId() == playId) {
                    entity.playCountText.set(String.valueOf(entity.getPlayCount() + 1));
                    entity.setPlayCount(entity.getPlayCount() + 1);
                    break;
                }

            }
        }
    }
}
