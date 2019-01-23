package com.cheese.radio.ui.test;

import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityTestBinding;

import javax.inject.Inject;

/**
 * Rabies
 *
 * @author USER
 * Date:   2019-01-23
 * Time:   11:21
 */
@ModelView(R.layout.activity_test)
public class TestModel extends ViewModel<TestActivity, ActivityTestBinding> {
    @Inject TestModel() { }
    private int value=0;
    @Override
    public void attachView(Bundle savedInstanceState, TestActivity testActivity) {
        super.attachView(savedInstanceState, testActivity);
    }

    public void onClick(View view) {
        getDataBinding().textView.setNewText(String.valueOf(--value),-1);
    }
    public void onAddClick(View view){
        getDataBinding().textView.setNewText(String.valueOf(++value));

    }
}
