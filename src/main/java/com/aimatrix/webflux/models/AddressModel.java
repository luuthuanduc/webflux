package com.aimatrix.webflux.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressModel(String addressLine, String postalCode, String city) {

    @JsonCreator
    public AddressModel(
        @JsonProperty("address_line") String addressLine,
        @JsonProperty("postal_code") String postalCode,
        @JsonProperty("city") String city) {
        this.addressLine = addressLine;
        this.postalCode = postalCode;
        this.city = city;
    }

}
