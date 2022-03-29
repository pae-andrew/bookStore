package ru.learnUp.lesson23.hibernate.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "book_storage")
public class BookStorage {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Long id;

    @Column
    private int countOfBooks;
}
