package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "book_storage")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BookStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column
    private int countOfBooks;
}
