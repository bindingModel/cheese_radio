package com.cheese.radio.base;

import android.text.TextUtils;

import com.binding.model.data.encrypt.FormUnionParams;
import com.binding.model.data.encrypt.UnionTransParams;
import com.binding.model.util.BaseUtil;
import com.binding.model.util.ReflectUtil;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.util.MyBaseUtil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.FormUtfBody;
import okhttp3.RequestBody;
import retrofit2.http.Url;

/**
 * Created by arvin on 2017/12/4.
 */

public class IkeParams implements UnionTransParams<FormUtfBody> {


    public String sign;
    public String uuid;
    public String timestamp;
    public String token;


    @Override
    public FormUtfBody transParams() {
        FormUtfBody.Builder builder = new FormUtfBody.Builder();
        List<Field> fields = ReflectUtil.getAllFields(getClass(), new ArrayList<>());
        for (Field field : fields) {
            Object o = ReflectUtil.beanGetValue(field, this);
            if (o == null) continue;
            if(BaseUtil.isEncoded(field)) builder.addEncoded(BaseUtil.findQuery(field), encrypt(o));
            else builder.add(BaseUtil.findQuery(field), encrypt(o));
        }
        return builder.build();
    }

    public String getUuid() {
        return uuid = MyBaseUtil.getMacAddress();
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimestamp() {
        if (timestamp == null)
            timestamp = String.valueOf(System.currentTimeMillis());
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
            if (o == null) continue;
            hashMap.put(BaseUtil.findQuery(field), o.toString());
        }
        return sign = MyBaseUtil.getSign(hashMap);
    }

    public String getToken() {
        if (IkeApplication.isLogin()) return IkeApplication.getUser().getToken();
        return null;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getKey() {
        return "";
    }

    @Override
    public String encrypt(Object json) {
        String value = json.toString();
        try {
            value =  URLEncoder.encode(value,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }
}
