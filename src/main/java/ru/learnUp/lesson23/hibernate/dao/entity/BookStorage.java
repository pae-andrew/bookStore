package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "book_storage")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class BookStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Book book;

    @Column
    private int countOfBooks;

    public BookStorage(Book book, int count) {
        this.book = book;
        this.countOfBooks = count;
    }
}
