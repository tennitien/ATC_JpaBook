package jpabook.jpabook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

//   OneToOne & song phuong
    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded //ed: bi phu thuoc
    private Address address;

    // DeliveryStatus: enum(ready, camp)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}
