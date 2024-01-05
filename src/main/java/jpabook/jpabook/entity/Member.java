package jpabook.jpabook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
//            (strategy = GenerationType.IDENTITY)
    @Column (name = "member_id")
    private Long id;

    private String name;

    // @Embeddable(Address_class) // Value_type_table: bao gom gia tri tu cac bang khac
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // qhe song phuong-> 1 cai thay doi thi ca 2 thay doi
    private List<Order> orders= new ArrayList<>(); // List -> no se lam moi lai


}
