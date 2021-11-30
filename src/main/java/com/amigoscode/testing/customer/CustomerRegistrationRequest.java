package com.amigoscode.testing.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;

@Getter
@ToString
public class CustomerRegistrationRequest {

    @Valid
    private Customer customer;

    public CustomerRegistrationRequest(@JsonProperty("customer") Customer customer){
        this.customer = customer;
    }

}
