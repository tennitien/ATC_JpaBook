package jpabook.jpabook.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id;

//    @NotEmpty(message = "Name book not empty")
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

}
