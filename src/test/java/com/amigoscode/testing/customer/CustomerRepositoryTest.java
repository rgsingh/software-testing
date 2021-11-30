package com.amigoscode.testing.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @Test
    void shouldSelectCustomerByPhoneNumber() {
        // Given
        UUID id = UUID.randomUUID();
        String phoneNumber = "0000";
        Customer customer = new Customer(id, "Rai", phoneNumber);
        underTest.save(customer);

        // When
        Optional<Customer> optionalCustomer = underTest.selectCustomerByPhoneNumber(phoneNumber);

        // Then
        Assertions.assertThat(optionalCustomer)
                .isPresent()
                .hasValueSatisfying(c -> {
                    Assertions.assertThat(c.getPhoneNumber()).isEqualTo(phoneNumber);
                });

    }

    @Test
    void shouldSaveCustomer() {
        // Given
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Rai", "0000");

        // When
        underTest.save(customer);

        // Then
        Optional<Customer> optionalCustomer = underTest.findById(id);
        Assertions.assertThat(optionalCustomer)
                .isPresent()
                .hasValueSatisfying(c -> {
                    Assertions.assertThat(c.getId()).isEqualTo(id);
                    Assertions.assertThat(c.getName()).isEqualTo("Rai");
                    Assertions.assertThat(c.getPhoneNumber()).isEqualTo("0000");
                });
    }
}
