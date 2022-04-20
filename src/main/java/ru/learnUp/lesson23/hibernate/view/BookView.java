package ru.learnUp.lesson23.hibernate.view;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;

@Data
@Component
public class BookView {

    private Long id;

    private String name;

    private int countOfShits;

    private int publishYear;

    private int price;

    private AuthorView author;

    public BookView mapToView(Book book) {
        BookView view = new BookView();
        view.setId(book.getId());
        view.setName(book.getName());
        view.setPrice(book.getPrice());
        view.setCountOfShits(book.getCountOfShits());
        view.setPublishYear(book.getPublishYear());
        view.setAuthor(new AuthorView(book.getAuthor().getId(), book.getAuthor().getFullName()));
        return view;
    }

    public Book mapFromView(BookView view) {
        Book book = new Book();
        book.setId(view.getId());
        book.setName(view.getName());
        book.setPrice(view.getPrice());
        book.setCountOfShits(view.getCountOfShits());
        book.setPublishYear(view.getPublishYear());
        book.setAuthor(new Author(view.getAuthor().getId(), view.getAuthor().getFullName()));
        return book;
    }
}
