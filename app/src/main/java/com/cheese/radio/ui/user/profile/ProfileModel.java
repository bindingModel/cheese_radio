package com.cheese.radio.ui.user.profile;

import android.app.Application;
import android.database.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Event;
import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulFlowTransformer;
import com.cheese.radio.databinding.ActivityProfileBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.user.edit.EditNameModel;
import com.cheese.radio.util.TimePickTool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.name;

/**
 * Created by 29283 on 2018/3/9.
 */
@ModelView(value = R.layout.activity_profile, event = R.id.ProfileModel)
public class ProfileModel extends ViewModel<ProfileActivity, ActivityProfileBinding> {

    public ObservableField<String> mDate = new ObservableField<>();
    public ObservableField<String> mSex = new ObservableField<>();
    private TimePickTool pickTool;

    @Inject
    ProfileModel() {
    }

    @Inject
    RadioApi api;
    private ProfileParams params;
    private OptionsPickerView sexPicker;
    private List<String> babySex = new ArrayList<>();
    private List<String> select = new ArrayList<String>();

    @Override
    public void attachView(Bundle savedInstanceState, ProfileActivity activity) {
        super.attachView(savedInstanceState, activity);
        pickTool = new TimePickTool(mDate, activity);
        initSexPicker();
        params = new ProfileParams("setProperty");
        getDataBinding().setParams(params.setMsg(IkeApplication.getUser().getUserEntity()));
    }


    public void onSelectClick(View view) {
        pickTool.show();
    }

    public void onPropertyClick(View view) {
        if (params.isValidName((TextView) view)) {
            params.setBirthday(mDate.get());
            api.setProperty(params).compose(new ErrorTransform<>()).subscribe(stringInfoEntity -> {
                        BaseUtil.toast(stringInfoEntity.getMessage());
                        if (stringInfoEntity.getCode() == 0) IkeApplication.getUser().setUserEntity(params);
                        Model.dispatchModel("updataUI");
                    }

            );
        }
    }

    public void onEditNameClick(View view) {
        ARouterUtil.navigation(name);
    }

    public void updataUI() {
        getDataBinding().setParams(params);
    }

    @Override
    public int onEvent(View view, Event event, Object... args) {
        if (event instanceof EditNameModel) {
            EditNameModel model = (EditNameModel) (EditNameModel) event;
            IkeApplication.getUser().getUserEntity().setNickName(model.name.get());
            params.setNickName(model.name.get());
            updataUI();
        }
        return 1;
    }

    public void onSelectSex(View view) {
        sexPicker.show();
    }

    private void initSexPicker() {
        babySex.add("M");
        babySex.add("F");
        select.add("男孩");
        select.add("女孩");
        sexPicker = new OptionsPickerView.Builder(getT(), (options1, options2, options3, v) -> {
            params.setSex(babySex.get(options2));
            mSex.set(select.get(options2));
        }
        ).build();
        sexPicker.setNPicker(new ArrayList<String>(), select, new ArrayList<String>());
    }
}
