package spring.domain.value_type;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
