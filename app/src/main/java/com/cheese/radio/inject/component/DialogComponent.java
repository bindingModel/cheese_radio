package com.cheese.radio.inject.component;

import com.cheese.radio.inject.module.DialogModule;
import com.cheese.radio.inject.scope.FragmentScope;

import dagger.Component;

/**
 * Created by arvin on 2018/2/1.
 */


@FragmentScope
@Component(dependencies = AppComponent.class,modules={DialogModule.class})
public interface DialogComponent {
}
