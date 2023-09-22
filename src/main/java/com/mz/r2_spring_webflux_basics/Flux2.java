package com.mz.r2_spring_webflux_basics;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Flux2 {

    public static void main(String[] args) throws InterruptedException {

        // Combining Two Streams
        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .zipWith(Flux.range(0, Integer.MAX_VALUE),
                        (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
                .subscribe(System.out::println);

        System.out.println("------- interval");

        Flux.interval(Duration.ZERO, Duration.ofSeconds(1))
                .subscribe(System.out::println);

        Thread.sleep(5500L);

        System.out.println("------- concurrency, runs on parallel thread");

        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .subscribeOn(Schedulers.parallel())
                .subscribe(System.out::println);

        Thread.sleep(500L);
        System.out.println("------------ END");
    }
}
