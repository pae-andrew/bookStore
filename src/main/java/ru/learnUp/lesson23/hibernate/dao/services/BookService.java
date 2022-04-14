package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.services.BookStorageService;
import ru.learnUp.lesson23.hibernate.dao.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
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

    @Transactional
    @CacheEvict(value = "book", key = "#book.id")
    public void update(Book book) {
        bookRepository.save(book);
    }
}
