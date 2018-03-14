package com.cheese.radio.base.rxjava;

import com.binding.model.data.exception.ApiException;
import com.cheese.radio.base.InfoEntity;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;

/**
 * Created by arvin on 2017/11/30.
 */

public class RestfulFlowTransformer<T> implements FlowableTransformer<InfoEntity<T>, T> {
    private final BackpressureStrategy model;

    public RestfulFlowTransformer() {
        this(BackpressureStrategy.ERROR);
    }

    public RestfulFlowTransformer(BackpressureStrategy model){
        this.model = model;
    }

    @Override
    public Publisher<T> apply(Flowable<InfoEntity<T>> upstream) {
        return upstream
                .concatMap(entity -> Flowable.create(flowableEmitter -> {
                    try {
                        switch (entity.getCode()) {
                            case 0:if (entity.getData() != null) flowableEmitter.onNext(entity.getData());break;
                            default:throw new ApiException(entity.getMessage());
                        }
                    } catch (Exception e) {
                        flowableEmitter.onError(e);
                    } finally {
                        flowableEmitter.onComplete();
                    }
                }, model)
        );
    }
}
