package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "book_storage")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BookStorage implements Serializable {

    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column
    private int countOfBooks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookStorage that = (BookStorage) o;
        return book != null && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
