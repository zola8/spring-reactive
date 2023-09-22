package com.mz.r2_spring_webflux_basics.OpenTheDoor2;

import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

public class Door extends Mono<DoorSignal> implements Subscription {
    CoreSubscriber<? super DoorSignal> actual;

    @Override

    public void subscribe(CoreSubscriber<? super DoorSignal> actual) {
        this.actual = actual;
        actual.onSubscribe(this);
    }

    @Override
    public void request(long l) {
        /*do nothing, the DOOR_RING is not generated here anymore,the signal is generated when the delivery person knocks the door*/
    }

    @Override
    public void cancel() {
        //do nothing
    }

}