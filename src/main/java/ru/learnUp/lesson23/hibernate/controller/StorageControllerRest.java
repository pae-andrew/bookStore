package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.filters.StorageFilter;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;
import ru.learnUp.lesson23.hibernate.dao.services.BookStorageService;
import ru.learnUp.lesson23.hibernate.view.BookStorageView;

import java.util.List;

@RestController
@RequestMapping("rest/storage")
public class StorageControllerRest {

    private final BookStorageService bookStorageService;
    private final BookStorageView mapper;
    private final BookService bookService;

    public StorageControllerRest(BookStorageService bookStorageService, BookStorageView mapper,
                                 BookService bookService) {
        this.bookStorageService = bookStorageService;
        this.mapper = mapper;
        this.bookService = bookService;
    }

    // get storage
    @GetMapping
    public List<BookStorageView> getStorage(
            @RequestParam(value = "bookName", required = false) String bookName,
            @RequestParam(value = "address", required = false) String address
    ) {
        return mapper.mapToViewList(bookStorageService.getBookStorageBy(new StorageFilter(bookName, address)));
    }

    @GetMapping("/{storageId}")
    public BookStorageView getStorageById(@PathVariable("storageId") Long storageId) {
        return mapper.mapToView(bookStorageService.getBookStorageById(storageId));
    }

    // add storage
    @PostMapping
    public BookStorageView createStorage(@RequestBody BookStorageView body) {
        BookStorage storage = mapper.mapFromView(body, bookService, bookStorageService);
        BookStorage createdStorage = bookStorageService.update(storage);
        return mapper.mapToView(createdStorage);
    }

    // update storage
    @PutMapping("/{storageId}")
    public BookStorageView updateStorage(
            @PathVariable("storageId") Long storageId,
            @RequestBody BookStorageView body
    ) {

        BookStorage storage = bookStorageService.getBookStorageById(storageId);

        if (storage.getCountOfBooks() != body.getCountOfBooks()) {
            storage.setCountOfBooks(body.getCountOfBooks());
        }

        BookStorage updated = bookStorageService.update(storage);

        return mapper.mapToView(updated);

    }

    // delete storage
    @DeleteMapping("/{storageId}")
    public Boolean deleteStorage(@PathVariable("storageId") Long id) {
        return bookStorageService.deleteStorage(id);
    }
}
