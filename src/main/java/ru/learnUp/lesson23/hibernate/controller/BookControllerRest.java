package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.filters.BookFilter;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;
import ru.learnUp.lesson23.hibernate.view.BookView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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
            @RequestParam(value = "name", required = false) String name
    ) {
        return mapper.mapToViewList(bookService.getBooksBy(new BookFilter(name)));
    }

    @GetMapping("/{bookId}")
    public BookView getBook(@PathVariable("bookId") Long bookId) {
        return mapper.mapToView(bookService.getBookById(bookId));
    }

    // add book
    @PostMapping
    public BookView createBook(@RequestBody BookView body) {
        if (body.getId() != null) {
            throw new EntityExistsException("Book id must be null");
        }
        Book book = mapper.mapFromView(body);
        Book createdBook = bookService.createBook(book);
        return mapper.mapToView(createdBook);
    }

    // update book
    @PutMapping("/{bookId}")
    public BookView updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestBody BookView body
    ) {
        if (body.getId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(bookId, body.getId())) {
            throw new RuntimeException("Entity has bad id");
        }

        Book book = bookService.getBookById(bookId);

        if (!book.getName().equals(body.getName())) {
            book.setName(body.getName());
        }

        if (book.getPrice() != body.getPrice()) {
            book.setPrice(body.getPrice());
        }

//        if (book.getStorages() == null && body.getStorage() != null) {
//            book.setStorages(new BookStorage(body.getStorage().getId(), book, body.getStorage().getCountOfBooks()));
//        }
//
//        if (book.getStorages().getCountOfBooks() != body.getStorage().getCountOfBooks()) {
//            book.getStorages().setCountOfBooks(body.getStorage().getCountOfBooks());
//        }

        Book updated = bookService.update(book);

        return mapper.mapToView(updated);

    }

    // delete book
    @DeleteMapping("/{bookId}")
    public Boolean deleteBook(@PathVariable("bookId") Long id) {
        return bookService.delete(id);
    }
}