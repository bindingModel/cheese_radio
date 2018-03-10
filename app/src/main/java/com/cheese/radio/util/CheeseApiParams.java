package com.cheese.radio.util;

import com.binding.model.data.encrypt.FormSingleParams;
import com.binding.model.util.BaseUtil;
import com.binding.model.util.ReflectUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.FormBody;

/**
 * Created by 29283 on 2018/3/10.
 */

public class CheeseApiParams extends FormSingleParams {

    @Override
    public FormBody transParams() {
        FormBody.Builder builder = new FormBody.Builder();
        List<Field> fields = ReflectUtil.getAllFields(getClass(), new ArrayList<>());
        for (Field field : fields) {
            Object o = ReflectUtil.beanGetValue(field, this);
            if (o == null) continue;
            String str = o.toString();
            Class<?> paramsType = field.getType();
            if(paramsType.isArray()){
                Object[] os = (Object[])o;
                for(Object collection:os){
                    if(collection == null)continue;
                    builder.addEncoded(BaseUtil.findQuery(field), encrypt(collection.toString()));
                }
            }else if(Collection.class.isAssignableFrom(paramsType)){
                Collection<?> collections = (Collection)o;
                for (Object collection : collections) {
                    if(collection == null)continue;
                    builder.addEncoded(BaseUtil.findQuery(field), encrypt(collection.toString()));
                }
            }else{
                builder.addEncoded(BaseUtil.findQuery(field), encrypt(str));
            }
        }
        return builder.build();
    }

    @Override
    public String encrypt(Object json) {
        return json.toString();
    }
}
