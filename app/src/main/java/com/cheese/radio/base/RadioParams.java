package com.cheese.radio.base;

import com.binding.model.data.encrypt.SingleTransParams;
import com.binding.model.util.BaseUtil;
import com.binding.model.util.ReflectUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;

public class RadioParams implements SingleTransParams<FormBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
    @Override
    public FormBody transParams() {
        FormBody.Builder builder = new FormBody.Builder();
        List<Field> fields = ReflectUtil.getAllFields(getClass(), new ArrayList<>());
        for (Field field : fields) {
            Object o = ReflectUtil.beanGetValue(field, this);
            if (o == null) continue;
            builder.addEncoded(BaseUtil.findQuery(field), encrypt(o));
        }
        return builder.build();
    }

    @Override
    public String encrypt(Object json) {
        return null;
    }
}
