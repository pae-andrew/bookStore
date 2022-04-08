package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.stereotype.Service;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.repository.BookStorageRepository;

import java.util.List;

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
}
