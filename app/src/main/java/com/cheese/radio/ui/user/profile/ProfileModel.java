package com.cheese.radio.ui.user.profile;

import android.database.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityProfileBinding;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/9.
 */
@ModelView(R.layout.activity_profile)
public class ProfileModel extends ViewModel<ProfileActivity,ActivityProfileBinding> {

    public ObservableField<String> date = new ObservableField<>();
    @Inject ProfileModel(){}

    @Override
    public void attachView(Bundle savedInstanceState, ProfileActivity activity) {
        super.attachView(savedInstanceState, activity);
//        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
//
//            @Override
//        public void onTimeSelect(Date date, View v) {
//
//        }
//    }).build();

    }
}
