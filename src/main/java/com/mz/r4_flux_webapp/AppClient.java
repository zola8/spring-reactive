package com.mz.r4_flux_webapp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class AppClient {

    public static void main(String[] args) throws InterruptedException {

        WebClient client = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl("http://localhost:8080/posts")
            .build();


        // body - mono and flux = producers, Mono.just(body)
        Post thirdPost = new Post(null, "harmadik", "harmadik content");
        Mono<Post> createPost = client
            .post()
            .uri("/create")
            //.body(Mono.just(thirdPost), Post.class)
            .bodyValue(thirdPost)
            .retrieve()
            .bodyToMono(Post.class);

        System.out.println("New post created...");
        Thread.sleep(2000L);


        createPost.subscribe(createdPost -> {
                // Handle the successful response
                System.out.println("Post created: " + createdPost);
            },
            error -> {
                // Handle errors
                System.err.println("Error creating post: " + error.getMessage());
            });


        Thread.sleep(1000L);
//        System.out.println("Retrieving posts...");


        client
            .get()
            .uri("/all")
            .retrieve()
            // .exchangeToFlux(response -> response.bodyToFlux(Post.class))
            .bodyToFlux(Post.class)
//            .log()
//            .subscribe(post -> System.out.println("post: " + post))
        ;


//        Thread.sleep(2000L);
    }
}
