package com.cheese.radio.base.cycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.binding.model.util.ReflectUtil;
import com.cheese.radio.inject.component.DaggerServiceComponent;
import com.cheese.radio.inject.component.ServiceComponent;
import com.cheese.radio.ui.IkeApplication;

import java.lang.reflect.Method;

/**
 * Created by pc on 2017/9/14.
 */

public abstract class BaseService extends Service {
    private ServiceComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
