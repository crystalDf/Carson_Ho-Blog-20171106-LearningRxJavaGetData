package com.star.learningrxjavagetdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RxJava";

    private String mMemoryCache = null;
    private String mDiskCache = null;
    private String mNetwork = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mMemoryCache = "从内存缓存中获取数据";
//        mDiskCache = "从磁盘缓存中获取数据";
        mNetwork = "从网络获取数据";

        Observable<String> memoryCacheObservable = Observable.create(emitter -> {

            if (mMemoryCache != null) {
                emitter.onNext(mMemoryCache);
            } else {
                emitter.onComplete();
            }
        });

        Observable<String> diskCacheObservable = Observable.create(emitter -> {

            if (mDiskCache != null) {
                emitter.onNext(mDiskCache);
            } else {
                emitter.onComplete();
            }
        });

        Observable<String> networkObservable = Observable.create(emitter -> {

            if (mNetwork != null) {
                emitter.onNext(mNetwork);
            } else {
                emitter.onComplete();
            }
        });

        Observable
                .concat(memoryCacheObservable, diskCacheObservable, networkObservable)
                .firstElement()
                .subscribe(s -> Log.d(TAG,"最终获取的数据来源 = " + s));
    }
}
