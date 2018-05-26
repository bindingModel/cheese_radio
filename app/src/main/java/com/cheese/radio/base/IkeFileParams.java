package com.cheese.radio.base;

import com.binding.model.data.encrypt.SingleTransParams;
import com.binding.model.util.BaseUtil;
import com.binding.model.util.ReflectUtil;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.user.User;
import com.cheese.radio.util.MyBaseUtil;
import com.cheese.radio.util.NetUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static okhttp3.MultipartBody.FORM;

public class IkeFileParams implements SingleTransParams<MultipartBody> {
    public static final MediaType FORM_URL = MediaType.parse("application/x-www-form-urlencoded");
    public String sign;
    public String uuid;
    public String timestamp;
    public String token;

    public IkeFileParams() {
        timestamp = String.valueOf(System.currentTimeMillis());
    }

    public String getUuid() {
        return uuid = NetUtil.getMacAddress();
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
            if (o == null || o instanceof File) continue;
            hashMap.put(BaseUtil.findQuery(field), o.toString());
        }
        return sign = MyBaseUtil.getSign(hashMap);
    }

    public String getToken() {
        return User.isLogin ? IkeApplication.getUser().getToken() : "";
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public MultipartBody transParams() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(FORM);
        for (Field field : ReflectUtil.getAllFields(getClass())) {
            Object o = ReflectUtil.beanGetValue(field, this);
            if (o == null) continue;
            if (o instanceof File) {
                File file = (File) o;
                RequestBody requestBody = RequestBody.create(FORM, file);
                builder.addFormDataPart(BaseUtil.findQuery(field), BaseUtil.findQuery(field), requestBody);
            } else {
                builder.addFormDataPart(BaseUtil.findQuery(field), encrypt(o));
            }
        }
        return builder.build();
    }

    @Override
    public String encrypt(Object json) {
        return json.toString();
    }
}
