package com.cheese.radio.base.cycle;

import android.app.IntentService;

import com.binding.model.util.ReflectUtil;
import com.cheese.radio.inject.component.DaggerServiceComponent;
import com.cheese.radio.inject.component.ServiceComponent;
import com.cheese.radio.ui.IkeApplication;

import java.lang.reflect.Method;

/**
 * Created by pc on 2017/9/14.
 */

public abstract class BaseIntentService extends IntentService {
    private ServiceComponent component;

    public BaseIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        try {
            Method method = ServiceComponent.class.getDeclaredMethod("inject", getClass());
            ReflectUtil.invoke(method, getComponent(), this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public ServiceComponent getComponent() {
        if (component == null) {
            component = DaggerServiceComponent.builder()
                    .appComponent(IkeApplication.getAppComponent())
                    .build();
        }
        return component;
    }
}
