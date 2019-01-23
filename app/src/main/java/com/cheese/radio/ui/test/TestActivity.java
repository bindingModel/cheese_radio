package com.cheese.radio.ui.test;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.inject.component.ActivityComponent;

/**
 * Rabies
 *
 * @author USER
 * Date:   2019-01-23
 * Time:   11:20
 */
@Route(path = ActivityComponent.Router.test)
public class TestActivity extends BaseActivity<TestModel> {
}
