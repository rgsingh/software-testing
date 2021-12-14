package com.amigoscode.testing.customer;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Customer {

    @Id
    @EqualsAndHashCode.Exclude
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

}
