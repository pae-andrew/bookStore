package ru.learnUp.lesson23.hibernate.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_storage")
public class BookStorage implements Serializable {

    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column
    private int countOfBooks;

    public Book getBook() {
        return book;
    }
}
