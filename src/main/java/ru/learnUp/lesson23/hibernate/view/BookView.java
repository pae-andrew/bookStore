package ru.learnUp.lesson23.hibernate.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BookView {

    private Long id;

    private String name;

    private int countOfSheets;

    private int publishYear;

    private int price;

    private AuthorViewForBook author;

    private List<BookStorageView> storages;

    public BookView mapToView(Book book) {
        BookView view = new BookView();
        view.setId(book.getId());
        view.setName(book.getName());
        view.setPrice(book.getPrice());
        view.setCountOfSheets(book.getCountOfSheets());
        view.setPublishYear(book.getPublishYear());
        view.setAuthor(new AuthorViewForBook(book.getAuthor().getId(), book.getAuthor().getFullName()));
        if (book.getStorage() != null) {
            view.setStorages(
                    book.getStorage().stream()
                            .map(storage -> new BookStorageView(storage.getId(), storage.getCountOfBooks()))
                            .collect(Collectors.toList())
            );
        }
        return view;
    }

    public Book mapFromView(BookView view) {
        Book book = new Book();
        List<Book> books = new ArrayList<>();
        book.setId(view.getId());
        book.setName(view.getName());
        book.setPrice(view.getPrice());
        book.setCountOfSheets(view.getCountOfSheets());
        book.setPublishYear(view.getPublishYear());
        if (view.getStorages() != null) {
            book.setStorage(
                    view.getStorages().stream()
                            .map(storage -> new BookStorage(book, storage.getCountOfBooks()))
                            .collect(Collectors.toList())
            );
        }
        books.add(book);
        book.setAuthor(new Author(view.getAuthor().getId(), view.getAuthor().getFullName(), books));
        return book;
    }

    public List<BookView> mapToViewList(List<Book> books) {
        List<BookView> views = new ArrayList<>();
        for (Book book : books) {
            BookView view = new BookView();
            view.setId(book.getId());
            view.setName(book.getName());
            view.setPrice(book.getPrice());
            view.setCountOfSheets(book.getCountOfSheets());
            view.setPublishYear(book.getPublishYear());
            view.setAuthor(new AuthorViewForBook(book.getAuthor().getId(), book.getAuthor().getFullName()));
            if (book.getStorage() != null) {
                view.setStorages(
                        book.getStorage().stream()
                                .map(storage -> new BookStorageView(storage.getId(), storage.getCountOfBooks()))
                                .collect(Collectors.toList())
                );
            }
            views.add(view);
        }
        return views;
    }
}
