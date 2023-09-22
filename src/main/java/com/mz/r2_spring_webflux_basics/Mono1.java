package com.mz.r2_spring_webflux_basics;

import reactor.core.publisher.Mono;

public class Mono1 {

    public static void main(String[] args) throws InterruptedException {
        // Mono: A Publisher that emits 0 or 1 element.

        Mono<String> mono = Mono.just("John");
        Mono<String> mono2 = Mono.just("Peter");
        Mono<Object> monoEmpty = Mono.empty();
        Mono<Object> monoError = Mono.error(new Exception());
        System.out.println("------------ mono");

        mono.subscribe(
                value -> System.out.println(value),
                error -> error.printStackTrace(),
                () -> System.out.println("completed 1")
        );

        monoEmpty.subscribe(
                value -> System.out.println(value),
                error -> error.printStackTrace(),
                () -> System.out.println("completed 2")
        );

        monoError.subscribe(
                value -> System.out.println(value),
                error -> {
                    System.out.println("hiba tortent!");
                    error.printStackTrace();
                },
                () -> System.out.println("completed 3")
        );

        Thread.sleep(500L);
        System.out.println("------ ");

        mono.mergeWith(mono2)
                .log("merge log")
                .subscribe(System.out::println);

        Thread.sleep(500L);
        System.out.println("------------ END");
    }
}
