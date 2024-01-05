package jpabook.jpabook.entity.item;


import jakarta.persistence.*;
import jpabook.jpabook.entity.Category;
import jpabook.jpabook.exception.NotEnoughException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // quan he ke thua tu bang Con
//Phan biet cac table con
@DiscriminatorColumn(name = "dtype")

//abstract: de cho class khac ke thua
public abstract class Item {
    @Id
    @GeneratedValue
//            (strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    /*
     * increase
     * */
    public void increaseStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*
     * decrease
     * */
    public void decreaseStock(int quantity) {
        int restStock = this.stockQuantity - quantity;

        if (restStock < 0) {
            throw new NotEnoughException("We need more stock.");
        }

        this.stockQuantity = restStock;
    }



}
