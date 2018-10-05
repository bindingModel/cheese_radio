package com.cheese.radio.ui.home.clock;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Inflate;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.databinding.FragmentHomeClockBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.cheese.radio.inject.component.ActivityComponent.Router.enroll;

/**
 * Created by 29283 on 2018/3/13.
 */
@ModelView(R.layout.fragment_home_clock)
public class ClockModel extends RecyclerModel<ClockFragment, FragmentHomeClockBinding, ClockEnrollEntity> {
    @Inject
    ClockModel() {
    }

    @Override
    public void attachView(Bundle savedInstanceState, ClockFragment clockFragment) {
        super.attachView(savedInstanceState, clockFragment);

        initEntity();
        getDataBinding().recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                getDataBinding().dragView.onDragView(0,dy);
            }
        });
    }

    private void initEntity() {
        List<ClockEnrollEntity> entities = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            entities.add(new ClockEnrollEntity(i));
        }
        setRcHttp((offset1, refresh) -> Observable.just(entities));
    }

    public void onClick(View view) {
        ARouterUtil.navigation(enroll);
    }
}
