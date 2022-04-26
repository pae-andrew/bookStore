package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "booksOrder")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BooksOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn
    private Client client;

    @Column
    private int orderCost;

    @OneToMany
    private List<OrderDetails> orderDetailsList;
}
