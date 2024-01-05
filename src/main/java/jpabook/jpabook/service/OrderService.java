package jpabook.jpabook.service;

import jpabook.jpabook.entity.*;
import jpabook.jpabook.entity.item.Item;
import jpabook.jpabook.repository.ItemRepository;
import jpabook.jpabook.repository.MemberRepository;
import jpabook.jpabook.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * Register Order
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        //Retrieve Entities
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //Create Delivery Info
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //Create Order Item Info
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //Create Order
        Order order = Order.createOrder(member, delivery, orderItem);

        //Register Order
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * Cancel Order
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //Retrieve Order Entity
        Order order = orderRepository.findOne(orderId);

        //Cancel Order
        order.cancel();
    }
    
//    public List<Order> findAll (OrderSearch orderSearch){
//        return orderRepository.findAllByCriteria(orderSearch);
//    }

    /*
     * findOrders
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
