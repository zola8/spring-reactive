package com.mz.r2_spring_webflux_basics.OpenTheDoor2;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

public class John extends Mono<DoorSignal> implements Subscriber<DoorSignal> {
    private CoreSubscriber<? super DoorSignal> next;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(1);
    }

    @Override
    public void onNext(DoorSignal doorRingSignal) {
        try {
            System.out.println("wait 5 second to simulate John is doing other things");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        next.onNext(DoorSignal.DOOR_OPEN);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void subscribe(CoreSubscriber<? super DoorSignal> actual) {
        next = actual;
    }

}