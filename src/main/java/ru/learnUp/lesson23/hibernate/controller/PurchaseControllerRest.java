package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderDetails;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;
import ru.learnUp.lesson23.hibernate.dao.services.BooksOrderService;
import ru.learnUp.lesson23.hibernate.dao.services.ClientService;
import ru.learnUp.lesson23.hibernate.dao.services.OrderDetailsService;
import ru.learnUp.lesson23.hibernate.view.PurchaseFromView;

@RestController
@RequestMapping("/purchase")
public class PurchaseControllerRest {

    private final ClientService clientService;
    private final BooksOrderService orderService;
    private final OrderDetailsService detailsService;
    private final BookService bookService;

    public PurchaseControllerRest(ClientService clientService,
                                  BooksOrderService orderService,
                                  OrderDetailsService detailsService,
                                  BookService bookService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.detailsService = detailsService;
        this.bookService = bookService;
    }

    @PostMapping
    public String createPurchase(@RequestBody PurchaseFromView purchaseView) {

        BooksOrder order = new BooksOrder();
        order.setClient(clientService.getClientById(purchaseView.getClientId()));
        BooksOrder createdOrder = orderService.createBooksOrder(order);

        int i = -1;
        for (String bookName : purchaseView.getBookName()) {
            i++;
            OrderDetails detail = new OrderDetails();
            Book book = bookService.getBookByName(bookName);
            detail.setBook(book);
            detail.setBooksOrder(createdOrder);
            detail.setCountOfBook(purchaseView.getCountOfBooks().get(i));
            detail.setPriceOfBook(book.getPrice() * detail.getCountOfBook());
            detailsService.createOrderDetail(detail);
            createdOrder.setOrderCost(createdOrder.getOrderCost() + detail.getPriceOfBook());
            orderService.update(createdOrder);
        }

        return "Done";
    }
}
