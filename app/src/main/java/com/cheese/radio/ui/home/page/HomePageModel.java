package com.cheese.radio.ui.home.page;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.InfoEntity;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.base.rxjava.RestfulZipTransformer;
import com.cheese.radio.databinding.FragmentHomePageBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.home.page.entity.CategoryEntity;
import com.cheese.radio.ui.home.page.entity.RecommandEntity;
import com.cheese.radio.ui.media.play.PlayParams;
import com.cheese.radio.ui.user.my.push.NewMessageCountParams;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.cheese.radio.inject.component.ActivityComponent.Router.search;

/**
 * Created by 29283 on 2018/3/3.
 */
@ModelView(R.layout.fragment_home_page)
public class HomePageModel extends RecyclerModel<HomePageFragment,FragmentHomePageBinding,GridInflate>{
    @Inject
    HomePageModel() {
    }

    private static Integer flag=0;
    @Inject
    RadioApi api;
    public ObservableField<String> redTipCount=new ObservableField<>("0");
    public ObservableBoolean redTipBoolean=new ObservableBoolean(false);
    @Override
    public void attachView(Bundle savedInstanceState, HomePageFragment homePageFragment) {
        super.attachView(savedInstanceState, homePageFragment);
        IkeApplication.isLogin();
        getDataBinding().layoutRecycler.setVm(this);
        GridLayoutManager layoutManager = new GridLayoutManager(homePageFragment.getContext(), 4);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
        setEnable(true);
        setPageFlag(false);
        Disposable subscribe = api.getNewMessageCount(new NewMessageCountParams("newMessageCount")).compose(new RestfulTransformer<>())
                .subscribe(newMessageCountData -> {
                    redTipCount.set(String.valueOf(newMessageCountData.getCount()));
                    if (newMessageCountData.getCount() != null && newMessageCountData.getCount() != 0)
                        redTipBoolean.set(true);
                    else redTipBoolean.set(false);
                });
        setRoHttp((offset1, refresh) -> {
            return getZip();
        });
    }
    private Observable<List<GridInflate>> getZip() {
//        Observable<InfoEntity<List<CategoryEntity>>> categoriy = api.getGroupInfo(new PlayParams("contentInfo","123")).compose(new RestfulZipTransformer<>());
        Observable<InfoEntity<List<CategoryEntity>>> categoriy = api.getCategoriy(new HomePageParams("category")).compose(new RestfulZipTransformer<>());
        Observable<InfoEntity<List<RecommanData>>> recommandList = api.getRecommand(new HomePageParams("recommandList")).compose(new RestfulZipTransformer<>());
            flag=0;
        return Observable.zip(categoriy, recommandList, (cate, entity) -> {
            List<GridInflate> list = new ArrayList<>();
            if (cate.code() == 0 && cate.getData() != null &&  !cate.getData().isEmpty()) {
                list.addAll(cate.getData().subList(0,4));
            }
            if(entity.code()==0 &&entity.getData()!=null &&!entity.getData().isEmpty()){
                for (RecommanData data:entity.getData()) {
                    list.add(data);
                    if(data.getViewType().equals("list"))
                        for (RecommandEntity recommandEntity :data.getList() ) {
                            recommandEntity.setIndex(1);
                        }
                    list.addAll(data.getList());
                }

            }
            return list;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public void onSearchClick(View view){
        ARouterUtil.navigation(search);
    }
    public void onMessageClick(View view){ARouterUtil.navigation(ActivityComponent.Router.message);}
}
