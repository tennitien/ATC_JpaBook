package jpabook.jpabook.entity.item;


import jakarta.persistence.*;
import jpabook.jpabook.entity.Category;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories= new ArrayList<>();
}
