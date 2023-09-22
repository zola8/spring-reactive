package com.mz.r2_spring_webflux_basics.OpenTheDoor;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        /*
            Door: the data publisher
            DoorSignal: the data
            John: the DoorSignal.DOOR_RING subscriber
         */

        Door door = new Door();
        John john = new John();
        door.subscribe(john);

        Thread.sleep(500L);
        System.out.println("------------ END");
    }
}
