package com.mz.r1_java_flow;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class MainSubscriber {

    /*
        Push data from producers and let consumers take care of it.
     */

    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        EndSubscriber<String> subscriber = new EndSubscriber<>();

        publisher.subscribe(subscriber);
        List<String> items = List.of("1", "x", "2", "x", "3", "x");

        System.out.println("getNumberOfSubscribers: " + publisher.getNumberOfSubscribers());

        items.forEach(publisher::submit);

        Thread.sleep(5 * 1000);

        publisher.submit("alma");
        publisher.close();

        Thread.sleep(5 * 1000);
        System.out.println("-- END");

    }
}
