package com.cheese.radio.ui.user.guide;

import com.binding.model.adapter.pager.ViewPagerAdapter;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityGuideBinding;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/6.
 */
@ModelView(R.layout.activity_guide)
public class GuideModel extends PagerModel<GuideActivity, ActivityGuideBinding, GuideEntity> implements GridInflate<ActivityGuideBinding> {
    @Inject
    GuideModel() {
        super(new ViewPagerAdapter<>());
    }


    @Override
    public int getSpanSize() {
        return 0;
    }
}
