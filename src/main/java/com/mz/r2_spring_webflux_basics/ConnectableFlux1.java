package com.mz.r2_spring_webflux_basics;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ConnectableFlux1 {

    /*
    Currently, we’ve focused primarily on cold streams.
    These are static, fixed-length streams that are easy to deal with.
    A more realistic use case for reactive might be something that happens infinitely.

    For example, we could have a stream of mouse movements that constantly needs to be reacted to or a Twitter feed.
    These types of streams are called hot streams,
    as they are always running and can be subscribed to at any point in time, missing the start of the data.
     */

    public static void main(String[] args) throws InterruptedException {
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
                    while (true) {
                        fluxSink.next(System.currentTimeMillis());
                    }
                })
                .sample(Duration.ofSeconds(2))      // values will only be pushed to our subscriber every two seconds
                .publish();
        // + there are multiple strategies to reduce the amount of data sent downstream, such as windowing and buffering

        // calling subscribe() won’t cause it to start emitting, allowing us to add multiple subscriptions
        publish.subscribe(System.out::println);
        publish.subscribe(System.out::println);

        // If we try running this code, nothing will happen.
        // It’s not until we call connect(), that the Flux will start emitting:
        publish.connect();
    }
}
