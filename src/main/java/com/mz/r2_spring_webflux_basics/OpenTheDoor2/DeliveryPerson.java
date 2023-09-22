package com.mz.r2_spring_webflux_basics.OpenTheDoor2;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DeliveryPerson implements Subscriber<DoorSignal> {

    public void knockDoor(final Door door) {
        System.out.println("door knocked");
        door.actual.onNext(DoorSignal.DOOR_RING);//generate DoorSignal.DOOR_RING signal
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        //do nothing
    }

    @Override
    public void onNext(DoorSignal doorSignal) {
        if (doorSignal == DoorSignal.DOOR_OPEN) {
            System.out.println("enter the door");
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
