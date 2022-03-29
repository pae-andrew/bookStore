package ru.learnUp.lesson23.hibernate.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn
    private Book book;

    @Column
    private int countOfBooks;

    @Column
    private int priceOfBooks;
}
