package jpabook.jpabook.service;

import jpabook.jpabook.entity.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    private ItemService itemServicel;

    @Test
    void saveItem() {

    }

    @Test
    void findItem() {
    }

    @Test
    void findOne() {
    }
}