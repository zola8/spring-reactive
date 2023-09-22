package com.mz.r2_spring_webflux_basics.OpenTheDoor2;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        /*
            Door: the data publisher
            DoorSignal: the data
            John: the DoorSignal.DOOR_RING subscriber and DoorSignal.DOOR_OPEN publisher
         */

        Door door = new Door();
        John john = new John();
        DeliveryPerson deliveryPerson = new DeliveryPerson();

        door.subscribe(john);
        john.subscribe(deliveryPerson);

        deliveryPerson.knockDoor(door); //The knock door triggers the overall process

        Thread.sleep(500L);
        System.out.println("------------ END");
    }
}
