package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderDetails implements Serializable {

    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "books_order_id", nullable = false)
    private BooksOrder booksOrder;

    @OneToMany
    @JoinColumn
    @ToString.Exclude
    private List<Book> book;

    @Column
    private int[] countOfBooks;

    @Column
    private int[] priceOfBooks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderDetails that = (OrderDetails) o;
        return booksOrder != null && Objects.equals(booksOrder, that.booksOrder);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
