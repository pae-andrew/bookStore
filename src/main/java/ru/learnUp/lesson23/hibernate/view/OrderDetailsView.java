package ru.learnUp.lesson23.hibernate.view;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;
import ru.learnUp.lesson23.hibernate.dao.entity.Client;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderDetails;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Component
public class OrderDetailsView {

    private Long id;

    private BooksOrder booksOrder;

    private Book book;

    private int countOfBook;

    private int priceOfBook;

    public OrderDetailsView mapToView(OrderDetails orderDetails) {
        OrderDetailsView view = new OrderDetailsView();
        view.setId(orderDetails.getId());
        view.setBooksOrder(orderDetails.getBooksOrder());
        view.setBook(orderDetails.getBook());
        view.setCountOfBook(orderDetails.getCountOfBook());
        view.setPriceOfBook(orderDetails.getPriceOfBook());
        return view;
    }

    public OrderDetails mapFromView(OrderDetailsView view) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(view.getId());
        orderDetails.setBooksOrder(view.getBooksOrder());
        orderDetails.setBook(view.getBook());
        orderDetails.setCountOfBook(view.getCountOfBook());
        orderDetails.setPriceOfBook(view.getPriceOfBook());
        return orderDetails;
    }
}
