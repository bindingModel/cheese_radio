package com.cheese.radio.base;

import com.binding.model.data.encrypt.MultipartSingleParams;
import com.binding.model.util.BaseUtil;
import com.binding.model.util.ReflectUtil;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.user.User;
import com.cheese.radio.util.MyBaseUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class IkeFileParams extends MultipartSingleParams {

    public String sign;
    public String uuid;
    public String timestamp;
    public String token;

    public IkeFileParams() {
        timestamp = String.valueOf(System.currentTimeMillis());
    }

    public String getUuid() {
        return uuid = MyBaseUtil.getMacAddress();
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimest(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        HashMap<String, String> hashMap = new HashMap<>();
        List<Field> fields = ReflectUtil.getAllFields(getClass());
        for (Field field : fields) {
            if ("sign".equals(field.getName())) continue;
            Object o = ReflectUtil.beanGetValue(field, this);
            if (o == null||o instanceof File) continue;
            hashMap.put(BaseUtil.findQuery(field), o.toString());
        }
        return sign = MyBaseUtil.getSign(hashMap);
    }

    public String getToken() {
        return User.isLogin?IkeApplication.getUser().getToken():"";
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String encrypt(Object json) {
        return json.toString();
    }
}
