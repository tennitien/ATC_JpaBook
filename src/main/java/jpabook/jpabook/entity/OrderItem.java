package jpabook.jpabook.entity;

import jakarta.persistence.*;
import jpabook.jpabook.entity.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "order_item_id")
    private Long id;

    // OrderItem-> Item
    @ManyToOne(fetch = FetchType.LAZY) // LAZY: lay thong tin khi can thiet
    @JoinColumn(name = "item_id")
    private Item item;

    // OrderItem-> Order || Order-> OrderItem
    @ManyToOne(fetch = FetchType.LAZY) // LAZY: lay thong tin khi can thiet
    // chua order_id -> chủ -> khi ma orderItem sua ->order bi anh huong
    private Order order;

    private int orderPrice;
    private int count;
}
