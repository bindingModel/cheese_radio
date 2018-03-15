package com.cheese.radio.base;

import com.binding.model.data.encrypt.FormUnionParams;
import com.binding.model.util.BaseUtil;
import com.binding.model.util.ReflectUtil;
import com.cheese.radio.util.MyBaseUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * Created by arvin on 2017/12/4.
 */

public class IkeParams extends FormUnionParams{
    public String sign;
    public String uuid;
    public String timestamp;

    public String getUuid() {
        return uuid= MyBaseUtil.getMacAddress();
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimestamp() {
        if (timestamp==null)
            timestamp=MyBaseUtil.getTimestamp();
        return timestamp;
    }

    public void setTimest(String timestamp) {
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

    public String getSign() {
        HashMap<String,String> hashMap = new HashMap<>();
        List<Field> fields = ReflectUtil.getAllFields(getClass());
        for (Field field : fields) {
            if("sign".equals(field.getName()))continue;
            Object o = ReflectUtil.beanGetValue(field, this);
            if (o == null) continue;
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
