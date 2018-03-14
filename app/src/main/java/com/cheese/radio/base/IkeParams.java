package com.cheese.radio.base;

import com.binding.model.data.encrypt.FormUnionParams;
import com.binding.model.util.BaseUtil;
import com.binding.model.util.ReflectUtil;
import com.cheese.radio.util.MyBaseUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by arvin on 2017/12/4.
 */

public class IkeParams extends FormUnionParams{
    public String sign;

    public String getSign() {
        HashMap<String,String> hashMap = new HashMap<>();
        List<Field> fields = ReflectUtil.getAllFields(getClass());
        for (Field field : fields) {
            Object o = ReflectUtil.beanGetValue(field, this);
            if (o == null||"sign".equals(field.getName())) continue;
            hashMap.put(BaseUtil.findQuery(field), o.toString());
        }
        return sign = MyBaseUtil.getSign(hashMap);
    }

    @Override
    public String getKey() {
        return "";
    }

    @Override
    public String encrypt(Object json) {
        return json.toString();
    }
}
