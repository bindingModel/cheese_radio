package com.cheese.radio.base.rxjava;

import android.arch.lifecycle.LifecycleRegistry;

import com.binding.model.data.exception.ApiException;
import com.cheese.radio.base.InfoEntity;

import java.util.ServiceConfigurationError;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by Zane on 16/4/9.
 * 更优雅的转换Observable去统一处理错误
 */
public class ErrorTransform<T> implements ObservableTransformer<InfoEntity<T>, InfoEntity<T>> {
    private int code = 1;
    private String errorMessage = "";
    private LifecycleRegistry registry;

    public ErrorTransform() {
    }

    @Override
    public ObservableSource<InfoEntity<T>> apply(Observable<InfoEntity<T>> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(tInfoEntity -> {
//                    code = tInfoEntity.getCode();
//                    switch (tInfoEntity.getCode()) {
//                        case 11095:
//                        case 11093:
//                        case 11092:
//                    }
//                })
                .onErrorResumeNext(throwable -> {
                    code = 99999;
                    if (throwable instanceof HttpException) {
                        throwable.printStackTrace();
                        HttpException response = (HttpException) throwable;
                        code = response.code();
                        switch (response.code()) {
                            case 401:errorMessage = "token无效" + response.message();break;
                            case 402:errorMessage = "数据库连接错误" + response.message();break;
                            case 403:errorMessage = "无记录" + response.message();break;
                            case 405:errorMessage = "token无效" + response.message();break;
                            case 400:errorMessage = "参数为空" + response.message();break;

                            default:errorMessage = "未知错误" + response.message();break;
                        }
                    } else if (throwable instanceof ServiceConfigurationError) {
                        errorMessage = "服务器错误";
                    }else if(throwable instanceof ApiException){
                        ApiException exception = (ApiException)throwable;
                        code = exception.getCode();
                        errorMessage = exception.getMessage();
                        exception.printStackTrace();
                    } else  {
                        errorMessage = "网络错误";

                    }
                    return Observable.create(subscriber -> {
                        InfoEntity<T> infoEntity = new InfoEntity<T>();
                        infoEntity.setCode(code);
                        infoEntity.setMessage(errorMessage);
                        subscriber.onNext(infoEntity);
                    });
                });
    }

}
