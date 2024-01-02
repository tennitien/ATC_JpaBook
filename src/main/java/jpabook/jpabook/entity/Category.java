package jpabook.jpabook.entity;

import jakarta.persistence.*;
import jpabook.jpabook.entity.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;


    @ManyToMany
//    @JoinColumn // lam entity chu
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;

    //    Quan he giua parent vs child
    @ManyToOne(fetch = FetchType.LAZY) // LAZY: lay thong tin khi can thiet
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child;
}
