package jpabook.jpabook.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jpabook.jpabook.entity.*;
import jpabook.jpabook.entity.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static jpabook.jpabook.entity.QMember.member;
import static jpabook.jpabook.entity.QOrder.order;

@Repository
public class OrderRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;


    public OrderRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Order order) {
        em.persist(order);
    }

    //findOne
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    //find
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Order, Member> m = o.join("member", JoinType.INNER); //회원과 조인
        List<Predicate> criteria = new ArrayList<>();
        //Filter By Order Status
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"),
                    orderSearch.getOrderStatus());
            criteria.add(status);
        }
        //Filter By Member Name
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name =
                    cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName()
                            + "%");
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }

    /*
     * findAll
     */
    public List<Order> findAll(OrderSearch orderSearch) {

        /*
         * Dang tinh------

        QOrder order = QOrder.order;
        QMember member = QMember.member;
        return query.select(order)
                .from(order)
                .join(order.member, member)
                .where(order.status.eq(orderSearch.getOrderStatus()),
//                        member.name.like("%Kim%") // many where
                        member.name.contains(orderSearch.getMemberName())
                )
                .limit(1000)
                .fetch();
   */

//        ----------------------
        /*
         * Dang dong
         * Add static QMember.member, QOrder.order
         */
        return query.select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEqual(orderSearch.getOrderStatus()),
                        nameLike(orderSearch.getMemberName()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression nameLike(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        return member.name.contains(name);
    }

    private BooleanExpression statusEqual(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return order.status.eq(status);
    }

    /*
     * findAll--end
     */
}

