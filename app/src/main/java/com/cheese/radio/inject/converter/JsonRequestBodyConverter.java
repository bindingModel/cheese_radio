package com.cheese.radio.inject.converter;

import com.binding.model.data.encrypt.SingleTransParams;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:04
 * modify developer：  admin
 * modify time：15:04
 * modify remark：
 *
 * @version 2.0
 */


public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        if (value instanceof SingleTransParams) {
            return ((SingleTransParams) value).transParams();
        }else{
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            adapter.toJson(writer, value);
            writer.close();
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }
    }
}
