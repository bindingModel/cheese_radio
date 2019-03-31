package com.cheese.radio.inject.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by arvin on 2018/2/1.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface DialogScope {
}
