package com.cheese.radio.inject.component;

/**
 * Created by pc on 2017/9/4.
 */

import com.cheese.radio.inject.module.ServiceModule;
import com.cheese.radio.inject.scope.ServiceScope;

import dagger.Component;

@ServiceScope
@Component(dependencies = AppComponent.class, modules = {ServiceModule.class})
public interface ServiceComponent {
}
