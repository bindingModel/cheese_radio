package com.cheese.radio.ui.user.service.center;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityServiceCenterBinding;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/30.
 */
@ModelView(R.layout.activity_service_center)
public class CenterModel extends ViewHttpModel<CenterActivity, ActivityServiceCenterBinding, Object> {

    @Inject CenterModel(){}



    @Override
    public void accept(Object o) throws Exception {

    }
}
