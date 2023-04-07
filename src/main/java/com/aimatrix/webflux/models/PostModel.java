package com.aimatrix.webflux.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PostModel(String body, String title, Integer id, Integer userId) {

    @JsonCreator
    public PostModel(
        @JsonProperty("body") String body,
        @JsonProperty("title") String title,
        @JsonProperty("id") Integer id,
        @JsonProperty("userId") Integer userId) {
        this.body = body;
        this.title = title;
        this.id = id;
        this.userId = userId;
    }

}
