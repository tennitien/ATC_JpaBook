package jpabook.jpabook.controller;

import jpabook.jpabook.entity.item.Book;
import jpabook.jpabook.entity.item.Item;
import jpabook.jpabook.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;


    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("items/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);

        return "redirect:/";
    }
    
    @GetMapping("/items")
    public String list (Model model){
        List<Item> items=itemService.findItems();
        model.addAttribute("items",items);
        return "/items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        // Retrieve Item Entity
        Book item = (Book) itemService.findOne(itemId);

        // Create BookForm Object
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        // Add BookForm to Model
        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping("/items/{id}/edit")
    public String updateItem(BookForm form) {
//        Book item = new Book();
//        item.setId(form.getId());
//        item.setName(form.getName());
//        item.setPrice(form.getPrice());
//        item.setStockQuantity(form.getStockQuantity());
//        item.setAuthor(form.getAuthor());
//        item.setIsbn(form.getIsbn());
//
//        itemService.saveItem(item);
        itemService.updateItem(form.getId(), form.getName(),
                form.getPrice(), form.getStockQuantity());

        return"redirect:/items";
    }
}
