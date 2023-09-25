package com.mz.r4_flux_webapp;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PostRepository {

    private static final List<Post> DATA = new ArrayList<>();

    static {
        DATA.add(new Post(UUID.randomUUID(), "post one", "content of post one"));
        DATA.add(new Post(UUID.randomUUID(), "post two", "content of post two"));
    }

    Flux<Post> findAll() {
        return Flux.fromIterable(DATA);
    }

    Mono<Post> findById(UUID id) {
        Optional<Post> optionalPost = DATA.stream().filter(post -> post.getId().equals(id)).findFirst();
        if (optionalPost.isPresent()) {
            return Mono.just(optionalPost.get());
        }

        return Mono.empty();
    }

    Mono<Post> createPost(Post post) {
        Post saved = new Post(UUID.randomUUID(), post.getTitle(), post.getContent());
        DATA.add(saved);
        return Mono.just(saved);
    }

}
