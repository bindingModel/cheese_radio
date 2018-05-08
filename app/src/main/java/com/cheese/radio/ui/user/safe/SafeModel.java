package com.cheese.radio.ui.user.safe;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivitySafeBinding;

import javax.inject.Inject;

@ModelView(R.layout.activity_safe)
public class SafeModel extends ViewHttpModel<SafeActivity,ActivitySafeBinding,Object>{
    @Override
    public void accept(Object o) throws Exception {

    }
    @Inject SafeModel(){}

}
