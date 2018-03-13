package com.cheese.radio.ui.home.clock;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentHomeClockBinding;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/13.
 */
@ModelView(R.layout.fragment_home_clock)
public class ClockModel extends ViewModel<ClockFragment,FragmentHomeClockBinding>{
    @Inject ClockModel(){}
}
