package com.mz.r2_spring_webflux_basics.OpenTheDoor;

import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

public class Door extends Mono<DoorSignal> implements Subscription {
    private CoreSubscriber<? super DoorSignal> actual;

    @Override
    public void subscribe(CoreSubscriber<? super DoorSignal> actual) {
        this.actual = actual;
        actual.onSubscribe(this);
    }

    @Override
    public void request(long l) {
        actual.onNext(DoorSignal.DOOR_RING);
    }

    @Override
    public void cancel() {
        //do nothing
    }
}