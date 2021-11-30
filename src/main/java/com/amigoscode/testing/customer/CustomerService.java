package com.amigoscode.testing.customer;

import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    void registerNewCustomer(CustomerRegistrationRequest customerRegistrationRequest);
}
