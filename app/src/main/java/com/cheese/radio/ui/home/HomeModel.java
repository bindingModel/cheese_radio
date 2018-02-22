package com.cheese.radio.ui.home;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;

import com.binding.model.adapter.ILayoutAdapter;
import com.binding.model.adapter.pager.FragmentStateAdapter;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityHomeBinding;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/2/22.
 */
@ModelView(R.layout.activity_home)
public class HomeModel extends PagerModel<HomeActivity,ActivityHomeBinding,HomeEntity> {

    @Inject HomeModel(@ActivityFragmentManager FragmentManager fm) {
      super(new FragmentStateAdapter<>(fm));
  }

    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//        int position = group.indexOfChild(group.findViewById(checkedId));
//        boolean a = position == 2&&!back&&User.isLogin();
//        if(position !=2||User.isLogin){
//            lastPosition = position;
//            back = false;
//        }
    }
}
