package ru.learnUp.lesson23.hibernate.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BookStorageView {

    private Long id;

    private int countOfBooks;

    public BookStorageView mapToView(BookStorage bookStorage) {
        BookStorageView view = new BookStorageView();
        view.setId(bookStorage.getId());
        view.setCountOfBooks(bookStorage.getCountOfBooks());
        return view;
    }

    public BookStorage mapFromView(BookStorageView view) {
        BookStorage bookStorage = new BookStorage();
        bookStorage.setId(view.getId());
        bookStorage.setCountOfBooks(view.getCountOfBooks());
        return bookStorage;
    }
}
