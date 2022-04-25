package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.stereotype.Service;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;
import ru.learnUp.lesson23.hibernate.dao.repository.BooksOrderRepository;

import java.util.List;

@Service
public class BooksOrderService {

    private final BooksOrderRepository booksOrderRepository;

    public BooksOrderService(BooksOrderRepository booksOrderRepository) {
        this.booksOrderRepository = booksOrderRepository;
    }

    public BooksOrder createBooksOrder(BooksOrder booksOrder) {
        return booksOrderRepository.save(booksOrder);
    }

    public List<BooksOrder> getBooksOrders() {
        return booksOrderRepository.findAll();
    }

    public BooksOrder getBooksOrderById(Long id) {
        return booksOrderRepository.getById(id);
    }

    public BooksOrder addBookToOrder(Book book, int count) {
        BooksOrder order = new BooksOrder();
        return booksOrderRepository.save(order);
    }
}
