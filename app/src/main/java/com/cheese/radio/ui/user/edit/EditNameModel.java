package com.cheese.radio.ui.user.edit;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityEditNameBinding;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/11.
 */
@ModelView(R.layout.activity_edit_name)
public class EditNameModel extends ViewModel<EditNameActivity,ActivityEditNameBinding> implements TextWatcher {

   public ObservableField<Boolean> editSwitch=new ObservableField<>(false);

    @Inject EditNameModel(){}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (s!=null && s.length()!=0)
            editSwitch.set(true);
        else editSwitch.set(false);
    }
}
