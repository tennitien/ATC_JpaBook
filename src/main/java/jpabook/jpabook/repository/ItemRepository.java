package jpabook.jpabook.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpabook.entity.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    //save
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            //chinh sua
            em.merge(item);
        }
    }

    // findOne
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    // findAll
    public List<Item> findAll() {
        return em.createQuery("SELECT i from Item i", Item.class)
                .getResultList();
    }


}
