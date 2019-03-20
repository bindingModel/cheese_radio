package com.cheese.radio.inject.converter;

import com.binding.model.data.exception.ApiException;
import com.binding.model.data.util.JsonDeepUtil;
import com.cheese.radio.base.InfoEntity;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import timber.log.Timber;

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Type type;
    private Gson gson =new Gson();
    JsonResponseBodyConverter(TypeAdapter<T> adapter, Type type) {
        this.adapter = adapter;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        InfoEntity entity = null;
        Timber.i(json);
        try {
            if (type instanceof ParameterizedType) {
                Class cc = (Class) ((ParameterizedType) type).getRawType();
                if (InfoEntity.class.isAssignableFrom(cc)) {
                    entity = gson.fromJson(json, InfoEntity.class);
                    if (entity.code() != 0)
                        throw new ApiException(entity.getMessage(), entity.code(), json);
                }
            }
            return adapter.fromJson(json);
        }catch (Exception e){
            if(entity!=null){
                value.close();
                throw new ApiException(entity.getMessage(), entity.code(), json);
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            value.close();
        }
    }
}
