package com.cheese.radio.ui.startup.check;

import android.content.Intent;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.binding.model.model.ModelView;
import com.binding.model.model.PopupModel;

import com.cheese.radio.R;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.databinding.PopupCheckUpdataBinding;

import javax.inject.Inject;

@ModelView(R.layout.popup_check_updata)
public class CheckUpdateModel extends PopupModel<BaseActivity, PopupCheckUpdataBinding> {

    @Inject
    CheckUpdateModel() {
    }
    public  ObservableField<String> message=new ObservableField<>();
    public  ObservableField<String> url=new ObservableField<>("http://www.cheeseradio.com/");
    @Override
    public void attachView(Bundle savedInstanceState, BaseActivity baseActivity) {
        super.attachView(savedInstanceState, baseActivity);
    }
    public void onCancelClick(View view){
        getWindow().dismiss();
    }
    public void onUrlClick(View view){
        Uri uri=Uri.parse(url.get());
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        getT().startActivity(intent);
    }
}
