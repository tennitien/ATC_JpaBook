package jpabook.jpabook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders") //table_name different with class_name
public class Order {
    @Id
    @GeneratedValue
//            (strategy = GenerationType.IDENTITY)
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
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")//    // Order chua delivery_id -> order_chu
    private Delivery delivery;

    private LocalDateTime orderDate;

    //    @Enumerated(EnumType.ORDINAL) // so lieu trong bang duoi dang ConSo -> Order =1 ,Cancel=0
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    //=== Association Assist method ===
    // Member Entity
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    // OrderItem Entity
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // Delivery Entity

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==Business Logic Methods==//
    /*
     * Cancel Order
     */
    public void cancel() {
        //validate delivery status
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("Order cannot be canceled once they are delivered.");
        }
        this.setStatus(OrderStatus.CANCEL);

        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }

    }

    /*
     * Retrieve Total Order Price
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getOrderPrice();
        }
        return totalPrice;
    }

    //==Create Methods=//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order=new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDER);

        for (OrderItem orderItem : orderItems  ) {
            order.addOrderItem(orderItem);
        }

        return order;
    }
}
