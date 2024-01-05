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
    @Transactional
    public Long saveItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }
    /*
     * update
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item item = itemRepository.findOne(itemId);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }

    /*
     * retrieve all
     */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /*
    retrieve One
     */
    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }


}
