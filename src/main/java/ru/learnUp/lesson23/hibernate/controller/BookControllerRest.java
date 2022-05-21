package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.filters.BookFilter;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;
import ru.learnUp.lesson23.hibernate.view.BookView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("rest/bookshop")
public class BookControllerRest {

    private final BookService bookService;
    private final BookView mapper;

    public BookControllerRest(BookService bookService, BookView mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    // get books
    @GetMapping
    public List<BookView> getBooks(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "publishYear", required = false) String publishYear,
            @RequestParam(value = "price", required = false) String price
    ) {
        return mapper.mapToViewList(bookService.getBooksBy(new BookFilter(name, publishYear, price)));
    }

    @GetMapping("/{bookId}")
    public BookView getBook(@PathVariable("bookId") Long bookId) {
        return mapper.mapToView(bookService.getBookById(bookId));
    }

    // add book
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public BookView createBook(@RequestBody BookView body) {

        Book book = mapper.mapFromView(body, bookService);
        Book createdBook = bookService.createBook(book);
        return mapper.mapToView(createdBook);
    }

    // update book
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{bookId}")
    public BookView updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestBody BookView body
    ) {

        Book book = bookService.getBookById(bookId);

        if (!book.getName().equals(body.getName())) {
            book.setName(body.getName());
        }

        if (book.getPrice() != body.getPrice()) {
            book.setPrice(body.getPrice());
        }

        Book updated = bookService.update(book);

        return mapper.mapToView(updated);

    }

    // delete book
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{bookId}")
    public Boolean deleteBook(@PathVariable("bookId") Long id) {
        return bookService.delete(id);
    }
}
