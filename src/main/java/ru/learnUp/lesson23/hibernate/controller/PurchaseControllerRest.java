package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.*;
import ru.learnUp.lesson23.hibernate.dao.services.*;
import ru.learnUp.lesson23.hibernate.view.BookViewForPurchase;
import ru.learnUp.lesson23.hibernate.view.PurchaseFromView;

import java.util.Calendar;

@RestController
@RequestMapping("/purchase")
public class PurchaseControllerRest {

    private final BooksOrderService orderService;
    private final OrderDetailsService detailsService;
    private final BookService bookService;
    private final BookStorageService storageService;
    private final UserService userService;
    private final OrderHistoryService historyService;

    public PurchaseControllerRest(BooksOrderService orderService,
                                  OrderDetailsService detailsService,
                                  BookService bookService,
                                  BookStorageService storageService,
                                  UserService userService,
                                  OrderHistoryService historyService) {

        this.orderService = orderService;
        this.detailsService = detailsService;
        this.bookService = bookService;
        this.storageService = storageService;
        this.userService = userService;
        this.historyService = historyService;
    }

    @PostMapping
    public String createPurchase(@RequestBody PurchaseFromView purchaseView) {

        for (BookViewForPurchase bookFromView : purchaseView.getBooks()) {
            if (storageService.getStorageByBook(
                    bookService.
                            getBookByName(bookFromView.getBookName()))
                    .getCountOfBooks() < bookFromView.getCountOfBook()) {
                return "Not enough books: \"" + bookFromView.getBookName() + "\"\n"
                        + "We have just "
                        + storageService.getStorageByBook(bookService.
                                getBookByName(bookFromView.getBookName()))
                        .getCountOfBooks()
                        + " book/s...";
            }
        }

        StringBuilder result = new StringBuilder();
        BooksOrder order = new BooksOrder();
        OrderHistory orderHistory = new OrderHistory();
        User user = userService.loadUserByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        order.setClient(user.getClient());
        BooksOrder createdOrder = orderService.createBooksOrder(order);

        for (BookViewForPurchase bookFromView : purchaseView.getBooks()) {
            OrderDetails detail = new OrderDetails();
            Book book = bookService.getBookByName(bookFromView.getBookName());
            storageService.buyBook(book, bookFromView.getCountOfBook());
            result.append("You bought \"").append(bookFromView.getBookName()).append("\" successfully.\n");
            detail.setBook(book);
            detail.setBooksOrder(createdOrder);
            detail.setCountOfBook(bookFromView.getCountOfBook());
            detail.setPriceOfBook(book.getPrice() * detail.getCountOfBook());
            detailsService.createOrderDetail(detail);
            createdOrder.setOrderCost(createdOrder.getOrderCost() + detail.getPriceOfBook());
        }
        orderService.update(createdOrder);

        orderHistory.setClient(user.getClient());
        orderHistory.setOrder(createdOrder);
        orderHistory.setCal(Calendar.getInstance());
        historyService.create(orderHistory);

        return result + "Thank you!";
    }
}
