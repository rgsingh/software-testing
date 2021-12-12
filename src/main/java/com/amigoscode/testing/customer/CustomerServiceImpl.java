package com.amigoscode.testing.customer;

import com.amigoscode.testing.exception.PaymentServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void registerNewCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        Customer customerRequest = customerRegistrationRequest.getCustomer();
        Optional<Customer> optionalCustomer =
                customerRepository.selectCustomerByPhoneNumber(customerRequest.getPhoneNumber());

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (customer.getName().equalsIgnoreCase(customerRequest.getName())) {
                return;
            } else {
                throw new PaymentServiceException("Customer already registered with that phone number", null);
            }
        }

        customerRepository.save(customerRegistrationRequest.getCustomer());
    }
}
