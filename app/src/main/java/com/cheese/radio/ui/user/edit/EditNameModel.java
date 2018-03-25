package com.cheese.radio.ui.user.edit;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Event;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityEditNameBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.IkeApplication;

import javax.inject.Inject;

import static com.binding.model.util.BaseUtil.getPhoneError;
import static com.binding.model.util.BaseUtil.isValidToast;
import static com.cheese.radio.util.MyBaseUtil.getNameError;

/**
 * Created by 29283 on 2018/3/11.
 */
@ModelView(R.layout.activity_edit_name)
public class EditNameModel extends ViewModel<EditNameActivity, ActivityEditNameBinding> implements TextWatcher {

    public ObservableField<Boolean> editSwitch = new ObservableField<>(false);

    public ObservableField<String> name = new ObservableField<>();

    @Inject
    EditNameModel() {
    }

    @Override
    public void attachView(Bundle savedInstanceState, EditNameActivity editNameActivity) {
        super.attachView(savedInstanceState, editNameActivity);
        name.set(IkeApplication.getUser().getUserEntity().getNickName());
        getDataBinding().editName.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        if (s != null && s.length() != 0) {
            editSwitch.set(true);
        } else editSwitch.set(false);

    }

    public void setNameClick(View view) {

        if ((isValidToast(view, getNameError(name.get())) & Event.event(R.id.ProfileModel, this, view) == 1))
            App.getCurrentActivity().finish();
    }

    public void onCleanClick(View view) {
        name.set("");
    }
}
