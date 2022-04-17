package ru.learnUp.lesson23.hibernate.dao.services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleStateException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.repository.BookStorageRepository;

import javax.persistence.LockModeType;
import java.util.List;

@Slf4j
@Service
public class BookStorageService {

    private final BookStorageRepository bookStorageRepository;

    public BookStorageService(BookStorageRepository bookStorageRepository) {
        this.bookStorageRepository = bookStorageRepository;
    }

    public BookStorage createBookStorage(BookStorage bookStorage) {
        return bookStorageRepository.save(bookStorage);
    }

    public List<BookStorage> getBookStorage() {
        return bookStorageRepository.findAll();
    }

    public BookStorage getBookStorageById(Long id) {
        return bookStorageRepository.getById(id);
    }

    @Lock(value = LockModeType.READ)
    public BookStorage getStorageByBook(Book book) {
        return bookStorageRepository.getByBook(book);
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public void update(BookStorage bookStorage) {
        bookStorageRepository.save(bookStorage);
    }

    @Transactional
    public int buyBook(Book book, int countOfBooks) {
        BookStorage bookStorage = getStorageByBook(book);
        bookStorage.setCountOfBooks(bookStorage.getCountOfBooks() - countOfBooks);
        update(bookStorage);
        return bookStorage.getCountOfBooks();
    }

    @Transactional
    public int addBook(Book book, int countOfBooks) {
        BookStorage bookStorage = getStorageByBook(book);
        bookStorage.setCountOfBooks(bookStorage.getCountOfBooks() + countOfBooks);
        update(bookStorage);
        return bookStorage.getCountOfBooks();
    }
}
