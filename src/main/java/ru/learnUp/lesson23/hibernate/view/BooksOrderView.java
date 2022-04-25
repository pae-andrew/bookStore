package ru.learnUp.lesson23.hibernate.view;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;
import ru.learnUp.lesson23.hibernate.dao.entity.Client;


@Data
@Component
public class BooksOrderView {
    private Long id;

    private Client client;

    private int orderCost;

    public BooksOrderView mapToView(BooksOrder booksOrder) {
        BooksOrderView view = new BooksOrderView();
        view.setId(booksOrder.getId());
        view.setClient(booksOrder.getClient());
        view.setOrderCost(booksOrder.getOrderCost());
        return view;
    }

    public BooksOrder mapFromView(BooksOrderView view) {
        BooksOrder booksOrder = new BooksOrder();
        booksOrder.setId(view.getId());
        booksOrder.setClient(view.getClient());
        booksOrder.setOrderCost(view.getOrderCost());
        return booksOrder;
    }
}
