package com.mz.r2_spring_webflux_basics;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Flux1 {

    public static void main(String[] args) throws InterruptedException {
        // Flux: A Publisher that emits 0 to N elements which can keep emitting elements forever.
        // It returns a sequence of elements
        // and sends a notification when it has completed returning all its elements.
        Flux<Integer> flux = Flux.just(1, 2, 3, 4);
        Flux<String> fluxString = Flux.fromArray(new String[]{"A", "B", "C"});
        Flux<String> fluxIterable = Flux.fromIterable(Arrays.asList("A", "B", "C"));
        Flux<Integer> fluxRange = Flux.range(2, 5);
        Flux<Long> fluxLong = Flux.interval(Duration.ofSeconds(10));

        System.out.println("------------ flux");
        // To Stream data and call subscribe method
        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(elements::add);
        System.out.println(elements);

        Thread.sleep(2000L);

        System.out.println("------------ stream");
        List<Integer> collected = Stream.of(1, 2, 3, 4)
                .collect(toList());
        System.out.println(collected);
        // Reactive is a push model, whereas the Java 8 Streams are a pull model.
        // In a reactive approach, events are pushed to the subscribers as they come in.
    }
}
