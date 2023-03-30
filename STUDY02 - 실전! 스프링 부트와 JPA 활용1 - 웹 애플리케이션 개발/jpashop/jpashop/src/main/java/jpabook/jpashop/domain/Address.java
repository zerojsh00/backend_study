package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable // JPA Entity 안의 Column을 하나의 객체로써 사용을 하고 싶다면 @Embeddable 어노테이션을 붙임
@Getter @Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
