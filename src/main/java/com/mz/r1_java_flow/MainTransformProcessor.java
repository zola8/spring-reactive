package com.mz.r1_java_flow;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class MainTransformProcessor {

    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        TransformProcessor<String, Integer> transformProcessor = new TransformProcessor<>(Integer::parseInt);
        EndSubscriber<Integer> subscriber = new EndSubscriber<>();
        List<String> items = List.of("1", "2", "3");

        publisher.subscribe(transformProcessor);
        transformProcessor.subscribe(subscriber);
        items.forEach(publisher::submit);
        publisher.close();

        Thread.sleep(5 * 1000);
        System.out.println("-- END");
    }
}
