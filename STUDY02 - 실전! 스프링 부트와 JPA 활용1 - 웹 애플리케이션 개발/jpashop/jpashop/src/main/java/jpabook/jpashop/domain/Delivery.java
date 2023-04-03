package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING) // EnumType은 STRING으로 하는 것이 늘 좋다 ! ORDINAL 절대 쓰지 말 것
    private DeliveryStatus status; // READY, COMP


}
