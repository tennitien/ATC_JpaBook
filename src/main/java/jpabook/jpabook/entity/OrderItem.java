package jpabook.jpabook.entity;

import jakarta.persistence.*;
import jpabook.jpabook.entity.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue
//            (strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
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


    //==Business Logic Methods==//
    /*
     * Cancel Order Item
     */
    public void cancel() {
        item.increaseStock(count);
    }

    /*
     * Retrieve Total Order Item Price
     */
    public int getTotalPrice() {
        return orderPrice * count;
    }

    //== Methods
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.decreaseStock(count);
        return orderItem;
    }

}
