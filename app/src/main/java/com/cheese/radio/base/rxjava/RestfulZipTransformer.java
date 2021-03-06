package com.cheese.radio.base.rxjava;


import com.binding.model.util.BaseUtil;
import com.cheese.radio.base.InfoEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

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


public class RestfulZipTransformer<T> implements ObservableTransformer<InfoEntity<T>, InfoEntity<T>> {
    @Override
    public ObservableSource<InfoEntity<T>> apply(Observable<InfoEntity<T>> upstream) {
        return upstream.compose(new ErrorTransform<>())
                .flatMap(entity -> Observable.create(
                        subscriber -> {
                            try{
                                subscriber.onNext(entity);
                            }catch(Exception e){
                                subscriber.onNext(new InfoEntity<>());
                                BaseUtil.toast(e);
                            }finally {
                                subscriber.onComplete();
                            }
                        })
                );
    }


}
