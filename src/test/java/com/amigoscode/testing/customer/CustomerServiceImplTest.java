package com.amigoscode.testing.customer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CustomerServiceImplTest {

    @MockBean
    private CustomerRepository mockCustomerRepository;

    @Autowired
    private CustomerService underTest;

    @Test
    void shouldRegisterNewCustomerWhenPhoneNumberNotTaken() {
        // Given
        final Customer customer =
                new Customer(UUID.randomUUID(), "John", "5552221111");
        CustomerRegistrationRequest customerRequest =
                new CustomerRegistrationRequest(
                        customer);
        // When
        Mockito.when(mockCustomerRepository.save(any())).thenReturn(customer);
        underTest.registerNewCustomer(customerRequest);
        // Then
        Mockito.verify(mockCustomerRepository, Mockito.times(1)).save(customer);
    }
}
