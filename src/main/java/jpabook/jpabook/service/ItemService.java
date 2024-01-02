package jpabook.jpabook.service;

import jpabook.jpabook.entity.item.Item;
import jpabook.jpabook.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    /*
     *Save item
     */
    public Long saveItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    /*
     * retrieve all
     */
    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    /*
    retrieve One
     */
    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }


}
