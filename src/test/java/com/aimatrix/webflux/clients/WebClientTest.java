package com.aimatrix.webflux.clients;

import com.aimatrix.webflux.models.PostModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

class WebClientTest {

    WebClient client;
    String baseUrl;

    @BeforeEach
    void setup() {
        client = WebClient.create();
        baseUrl = "https://jsonplaceholder.typicode.com";
    }

    @Test
    void getOnePostRequestTest() {
        Mono<PostModel> result = client.get()
            .uri(URI.create(String.format("%s/posts/1", baseUrl)))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(PostModel.class);
        PostModel post = result.block();

        Assertions.assertThat(post)
            .hasFieldOrPropertyWithValue("userId", 1);
    }

    @Test
    void getRequestDataTest() {
        Mono<ResponseEntity<PostModel>> result = client.get()
            .uri(URI.create(String.format("%s/posts/1", baseUrl)))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(PostModel.class);
        ResponseEntity<PostModel> response = result.block();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.hasBody()).isTrue();
    }

    @Test
    void getPostsTest() {
        Flux<PostModel> result = client.get()
            .uri(URI.create(String.format("%s/posts", baseUrl)))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(PostModel.class);
        Iterable<PostModel> posts = result.toIterable();

        Assertions.assertThat(posts).hasSize(100);
    }

    @Test
    void postRequestTest() {
        PostModel payload = new PostModel("body", "title", 101, 101);
        Mono<PostModel> result = client.post()
            .uri(URI.create(String.format("%s/posts", baseUrl)))
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(payload)
            .retrieve()
            .bodyToMono(PostModel.class);
        PostModel post = result.block();

        Assertions.assertThat(post).isEqualTo(payload);
    }
}
