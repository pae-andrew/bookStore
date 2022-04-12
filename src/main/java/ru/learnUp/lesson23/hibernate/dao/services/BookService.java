package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Cacheable(value = "Book")
    public Book getBookById(Long id) {
        return bookRepository.findBook1(id);
    }
}
