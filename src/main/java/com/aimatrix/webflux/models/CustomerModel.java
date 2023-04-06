package com.aimatrix.webflux.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public record CustomerModel(@Id String customerId,
                            String companyName,
                            String companyEmail,
                            String taxId,
                            AddressModel billingAddress,
                            AddressModel shippingAddress) {

    @JsonCreator
    public CustomerModel(
        @JsonProperty("customer_id") String customerId,
        @JsonProperty("company_name") String companyName,
        @JsonProperty("company_email") String companyEmail,
        @JsonProperty("tax_id") String taxId,
        @JsonProperty("billing_address") AddressModel billingAddress,
        @JsonProperty("shipping_address") AddressModel shippingAddress) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.taxId = taxId;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

}
