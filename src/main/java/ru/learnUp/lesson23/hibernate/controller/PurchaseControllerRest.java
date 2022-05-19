package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderDetails;
import ru.learnUp.lesson23.hibernate.dao.entity.User;
import ru.learnUp.lesson23.hibernate.dao.services.*;
import ru.learnUp.lesson23.hibernate.view.BookViewForPurchase;
import ru.learnUp.lesson23.hibernate.view.PurchaseFromView;

@RestController
@RequestMapping("/purchase")
public class PurchaseControllerRest {

    private final ClientService clientService;
    private final BooksOrderService orderService;
    private final OrderDetailsService detailsService;
    private final BookService bookService;
    private final BookStorageService storageService;
    private final UserService userService;

    public PurchaseControllerRest(ClientService clientService,
                                  BooksOrderService orderService,
                                  OrderDetailsService detailsService,
                                  BookService bookService, BookStorageService storageService, UserService userService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.detailsService = detailsService;
        this.bookService = bookService;
        this.storageService = storageService;
        this.userService = userService;
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

        StringBuilder result = new StringBuilder("");
        BooksOrder order = new BooksOrder();
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
            result.append("You bought \"" + bookFromView.getBookName() + "\" successfully.\n");
            detail.setBook(book);
            detail.setBooksOrder(createdOrder);
            detail.setCountOfBook(bookFromView.getCountOfBook());
            detail.setPriceOfBook(book.getPrice() * detail.getCountOfBook());
            detailsService.createOrderDetail(detail);
            createdOrder.setOrderCost(createdOrder.getOrderCost() + detail.getPriceOfBook());
            orderService.update(createdOrder);
        }

        return result.toString() + "Thank you!";
    }
}
