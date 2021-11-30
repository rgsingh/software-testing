package com.amigoscode.testing.customer;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerRegistrationControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private Gson gson;
    private UUID uuid;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        gson = new Gson();
        uuid = UUID.randomUUID();
    }

    @Test
    public void shouldUpdateCustomerWithValidRequest() throws Exception {

        mockMvc.perform(put("/api/v1/customer-registration/" + uuid)
                .content(jsonValidCustomerRegistrationRequest().getBytes())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

    }

    @Test
    public void shouldFailWithInvalidRequest() throws Exception {

        mockMvc.perform(put("/api/v1/customer-registration/" + uuid)
                .content(jsonInvalidCustomerRegistrationRequest().getBytes())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    private String jsonValidCustomerRegistrationRequest() {
        Customer customer = new Customer(uuid, "John Smith", "8885551212");
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(customer);
        return gson.toJson(customerRegistrationRequest);
    }

    private String jsonInvalidCustomerRegistrationRequest() {
        Customer customer= new Customer(uuid, "", "8675309");
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(customer);
        return gson.toJson(customerRegistrationRequest);
    }
}
