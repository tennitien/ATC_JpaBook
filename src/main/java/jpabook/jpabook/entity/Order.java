package jpabook.jpabook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders") //table_name different with class_name
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    // many_order with One_member
    // Order chua member_id -> order_chu
//    @ManyToOne(fetch = FetchType.EAGER) // EAGER: tu dong lay thong tin khi tao Order -> khong tot cho hieu nang
    @ManyToOne(fetch = FetchType.LAZY) // LAZY: lay thong tin khi can thiet
    @JoinColumn(name = "member_id") // Pk : de lay thong tin tu Member
    private Member member;

    // cascade: khi co moi quan he phu thuoc, Order thay doi thi OrderItem thay doi
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    //
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id") //    // Order chua delivery_id -> order_chu
    private Delivery delivery;

    private LocalDateTime orderDate;

    //    @Enumerated(EnumType.ORDINAL) // so lieu trong bang duoi dang ConSo -> Order =1 ,Cancel=0
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    //=== Association Assist method ===
    // Member Entity
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    // OrderItem Entity

    public void setOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // Delivery Entity

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
