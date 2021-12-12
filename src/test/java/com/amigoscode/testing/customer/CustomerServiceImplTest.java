package com.amigoscode.testing.customer;

import com.amigoscode.testing.exception.PaymentServiceException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
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

    @Test
    void shouldDoNothingWhenPhoneNumberIsForSameCustomer() {
        // Given
        final Customer persistedCustomer =
                new Customer(UUID.randomUUID(), "John", "5552221111");
        final Customer inflightCustomer =
                new Customer(UUID.randomUUID(), "John", "5552221111");

        CustomerRegistrationRequest customerRequest =
                new CustomerRegistrationRequest(
                        inflightCustomer);
        // When
        Mockito.when(mockCustomerRepository.selectCustomerByPhoneNumber(any()))
                .thenReturn(Optional.of(persistedCustomer));
        underTest.registerNewCustomer(customerRequest);
        // Then
        Mockito.verify(mockCustomerRepository, Mockito.times(0)).save(inflightCustomer);
    }

    @Test
    void shouldThrowExceptionWhenDifferentNamesForSamePhoneNumber() {
        // Given
        final Customer persistedCustomer =
                new Customer(UUID.randomUUID(), "Jane", "5552221111");
        final Customer inflightCustomer =
                new Customer(UUID.randomUUID(), "John", "5552221111");

        CustomerRegistrationRequest customerRequest =
                new CustomerRegistrationRequest(
                        inflightCustomer);
        // When
        Mockito.when(mockCustomerRepository.selectCustomerByPhoneNumber(any()))
               .thenReturn(Optional.of(persistedCustomer));
        Assertions.assertThatThrownBy(() -> {
            underTest.registerNewCustomer(customerRequest);
          //Then
        }).isInstanceOf(PaymentServiceException.class)
          .hasMessage("Customer already registered with that phone number");

    }
}
