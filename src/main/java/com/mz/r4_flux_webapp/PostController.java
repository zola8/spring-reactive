package com.mz.r4_flux_webapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/all")
    public Flux<Post> all() {
        return postRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Post> get(@PathVariable(value = "id") UUID id) {
        return postRepository.findById(id);
    }

    @PostMapping(value = "/create")
    public Mono<Post> create(Post post) {
        return postRepository.createPost(post);
    }

}
