package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "books_order_id", nullable = false)
    private BooksOrder booksOrder;

    @OneToOne
    @JoinColumn
    @ToString.Exclude
    private Book book;

    @Column
    private int countOfBook;

    @Column
    private int priceOfBook;
}
