package com.cheese.radio.ui.user.safe;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.databinding.ActivitySafeBinding;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.CheeseApplication;

import javax.inject.Inject;

@ModelView(value = R.layout.activity_safe,model = true)
public class SafeModel extends ViewHttpModel<SafeActivity,ActivitySafeBinding,Object>{
    @Override
    public void onNext(Object o)  {

    }
    @Inject SafeModel(){}

    public void onPhoneClick(View view){
        ARouterUtil.navigation(ActivityComponent.Router.phone);
    }
    public ObservableBoolean havaPhoneNumber=new ObservableBoolean(false);
    public ObservableField<String> phoneNumber=new ObservableField<>();
    @Override
    public void attachView(Bundle savedInstanceState, SafeActivity activity) {
        super.attachView(savedInstanceState, activity);
        upDataUI();
    }

    private void upDataUI(){
        String phone=CheeseApplication.getUser().getUserEntity().getMobile();
       if(!TextUtils.isEmpty(phone)) phone=phone.substring(0,3)+"****"+phone.substring(7,11);
        phoneNumber.set(phone);
        havaPhoneNumber.set(!TextUtils.isEmpty(CheeseApplication.getUser().getUserEntity().getMobile()));

   }
}
