package com.cheese.radio.ui.startup.check;

import android.content.Intent;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.binding.model.model.ModelView;
import com.binding.model.model.PopupModel;

import com.cheese.radio.R;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.databinding.PopupCheckUpdataBinding;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

@ModelView(R.layout.popup_check_updata)
public class CheckUpdateModel extends PopupModel<BaseActivity, PopupCheckUpdataBinding> {
    WindowManager.LayoutParams lp;
    @Inject
    CheckUpdateModel() {
    }
    public  ObservableField<String> message=new ObservableField<>("新的芝士电台正在向你狂奔而来");
    public  ObservableField<String> url=new ObservableField<>("http://www.cheeseradio.net/");
    @Override
    public void attachView(Bundle savedInstanceState, BaseActivity baseActivity) {
        super.attachView(savedInstanceState, baseActivity);
        setOnDismissListener(() -> getT().finish());
    }
    public void onCancelClick(View view){
        dismiss();
    }
    public void onUrlClick(View view){
        Uri uri=Uri.parse(url.get());
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        getT().startActivity(intent);
    }

    @Override
    public void show(Consumer<PopupWindow> consumer) {
        super.show(consumer);
        getT().getWindow().getAttributes();
        lp= getT().getWindow().getAttributes();
        lp.alpha = 0.3f;
        getT().getWindow().setAttributes(lp);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        lp.alpha=1.0f;
        getT().getWindow().setAttributes(lp);
    }

    public void setURL(String url){
        this.url.set(url);
    }

}
