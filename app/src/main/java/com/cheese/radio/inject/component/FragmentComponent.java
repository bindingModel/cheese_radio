package com.cheese.radio.inject.component;

import com.cheese.radio.inject.module.FragmentModule;
import com.cheese.radio.inject.scope.FragmentScope;
import com.cheese.radio.ui.home.circle.CircleFragment;
import com.cheese.radio.ui.home.clock.ClockFragment;
import com.cheese.radio.ui.home.mine.HomeMineFragment;
import com.cheese.radio.ui.home.page.HomePageFragment;
import com.cheese.radio.ui.media.anchor.entity.description.DescriptionFragment;
import com.cheese.radio.ui.media.anchor.entity.play.AnchorFragment;
import com.cheese.radio.ui.media.group.fragment.introduce.GroupIntroduceFragment;
import com.cheese.radio.ui.media.group.fragment.story.GroupStoryFragment;
import com.cheese.radio.ui.user.calendar.CalendarFragment;

import dagger.Component;


/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:29
 * modify developer：  admin
 * modify time：11:29
 * modify remark：
 *
 * @version 2.0
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules={FragmentModule.class})
public interface FragmentComponent {
    void inject(HomeMineFragment fragment);
    void inject(HomePageFragment fragment);
    void inject(CircleFragment fragment);
    void inject(ClockFragment fragment);
    void inject(AnchorFragment fragment);
    void inject(DescriptionFragment fragment);
    void inject(GroupStoryFragment fragment);
    void inject(GroupIntroduceFragment fragment);
    void inject(CalendarFragment fragment);
}
