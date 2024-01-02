package jpabook.jpabook.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable // Value_type_table: bao gom gia tri tu cac bang khac
@Getter @Setter
public class Address {
    private  String city;
    private  String street;
    private  String zipcode;
}
