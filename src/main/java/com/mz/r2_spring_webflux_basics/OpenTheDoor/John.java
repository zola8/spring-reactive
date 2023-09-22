package com.mz.r2_spring_webflux_basics.OpenTheDoor;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class John implements Subscriber<DoorSignal> {

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(DoorSignal doorBellSignal) {
        if (doorBellSignal == DoorSignal.DOOR_RING) {
            System.out.println("open the door");
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
