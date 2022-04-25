package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
