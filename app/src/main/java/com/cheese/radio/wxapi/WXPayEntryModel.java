package com.cheese.radio.wxapi;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityPayResultBinding;

import javax.inject.Inject;


/**
 * Created by arvin on 2018/1/20.
 */
@ModelView(R.layout.activity_pay_result)
public class WXPayEntryModel extends ViewModel<WXPayEntryActivity,ActivityPayResultBinding> {
    @Inject WXPayEntryModel() {}

}
