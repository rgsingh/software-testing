package com.amigoscode.testing.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer-registration")
public class CustomerRegistrationController {

    @Autowired
    private CustomerService customerService;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerNewCustomer(@Valid @RequestBody CustomerRegistrationRequest request,
                                        @PathVariable UUID id) {
        customerService.registerNewCustomer(request);
    }
}
