package com.amigoscode.testing.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    @Query(
            value = "select id, name, phone_number from customer where phone_number = :phoneNumber",
            nativeQuery = true
    )
    Optional<Customer> selectCustomerByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
