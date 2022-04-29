package ru.learnUp.lesson23.hibernate.view;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderDetails;
import ru.learnUp.lesson23.hibernate.dao.services.BooksOrderService;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class OrderDetailsView {

    private BookViewForDetails book;

    private int countOfBook;

    private int priceOfBook;

    public OrderDetailsView mapToView(OrderDetails orderDetails) {
        OrderDetailsView view = new OrderDetailsView();
        view.setBook(new BookViewForDetails(orderDetails.getBook().getName()));
        view.setCountOfBook(orderDetails.getCountOfBook());
        view.setPriceOfBook(orderDetails.getCountOfBook() *
                orderDetails.getBook().getPrice());
        return view;
    }

    public List<OrderDetailsView> mapToView(List<OrderDetails> orderDetailsList) {
        List<OrderDetailsView> views = new ArrayList<>();
        orderDetailsList.forEach(orderDetails -> {
            OrderDetailsView view = new OrderDetailsView();
            view.setBook(new BookViewForDetails(orderDetails.getBook().getName()));
            view.setCountOfBook(orderDetails.getCountOfBook());
            view.setPriceOfBook(orderDetails.getCountOfBook() *
                    orderDetails.getBook().getPrice());
        });
        return views;
    }

//    public OrderDetails mapFromView(OrderDetailsView view, BooksOrderService orderService) {
//        OrderDetails orderDetails = new OrderDetails();
//        orderDetails.setBooksOrder(view.getBooksOrder());
//        orderDetails.setBook(view.getBook());
//        orderDetails.setCountOfBook(view.getCountOfBook());
//        orderDetails.setPriceOfBook(view.getPriceOfBook());
//        return orderDetails;
//    }
}
