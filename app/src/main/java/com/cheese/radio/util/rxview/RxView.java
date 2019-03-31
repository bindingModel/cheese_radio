package com.cheese.radio.util.rxview;

import android.view.View;

import io.reactivex.Observable;

import static io.reactivex.android.MainThreadDisposable.verifyMainThread;

public class RxView {
    private static boolean viewEnable = true;

    public static Observable<Object> bindView(View view) {
        if (view == null)
            throw new NullPointerException("view == null");
        return Observable.create(e -> {
            verifyMainThread();
            view.setOnClickListener(v -> {
                e.onNext(1);
                view.setEnabled(viewEnable);
            });
        });
    }

    public static void setViewEnable(boolean viewEnable) {
        RxView.viewEnable = viewEnable;
    }
}
