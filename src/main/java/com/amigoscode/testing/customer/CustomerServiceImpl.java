package com.amigoscode.testing.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void registerNewCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        customerRepository.save(customerRegistrationRequest.getCustomer());
    }
}
