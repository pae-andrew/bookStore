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

    @OneToOne(optional = false)
    @JoinColumn(name = "books_order_id", nullable = false)
    private BooksOrder booksOrder;

    @OneToMany
    @JoinColumn
    @ToString.Exclude
    private List<Book> book;

    @Column
    private int countOfBooks;

    @Column
    private int priceOfBooks;
}
