package com.cheese.radio.base.rxjava;


import com.binding.model.data.exception.ApiException;
import com.cheese.radio.base.InfoEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:22
 * modify developer：  admin
 * modify time：11:22
 * modify remark：
 *
 * @version 2.0
 */


public class RestfulTransformer<T> implements ObservableTransformer<InfoEntity<T>, T> {
    @Override
    public ObservableSource<T> apply(Observable<InfoEntity<T>> upstream) {
        return upstream
                .compose(new ErrorTransform<>())
                .concatMap(entity -> Observable.create(
                        subscriber -> {
                            try {
                                Timber.i("code:%1d", entity.code());
                                switch (entity.code()) {
                                    case 0:
                                        if (entity.getData() != null) subscriber.onNext(entity.getData());
                                            break;

                                    default:
                                        throw new ApiException(entity.getMessage());
                                }
                            } catch (Exception e) {
                                subscriber.onError(e);
                            } finally {
                                subscriber.onComplete();
                            }
                        })
                );
    }
}
