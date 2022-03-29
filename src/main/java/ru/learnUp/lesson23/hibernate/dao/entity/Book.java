package ru.learnUp.lesson23.hibernate.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int countOfShits;

    @Column
    private int publishYear;

    @Column
    private int price;

    @ManyToOne
    @JoinColumn
    private Author author;
}
