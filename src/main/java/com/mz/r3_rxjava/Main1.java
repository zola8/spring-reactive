package com.mz.r3_rxjava;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class Main1 {

    public static void main(String[] args) throws InterruptedException {

//        Flowable.just("Hello World!").subscribe(System.out::println);

        // Single observable can only emit either a single successful value or an error. It does not emit onComplete event.

        //Create the observable
        Single<String> testSingle = Single.just("Hello World");

        //Create an observer
        Disposable disposable = testSingle
                .delay(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(
                        new DisposableSingleObserver<String>() {

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onSuccess(String value) {
                                System.out.println(value);
                            }
                        });
        Thread.sleep(3000);

        //start observing
        disposable.dispose();

    }
}
