package com.cheese.radio.inject.component;

import com.cheese.radio.inject.module.FragmentModule;
import com.cheese.radio.inject.scope.FragmentScope;

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
}
