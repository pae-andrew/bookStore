package ru.learnUp.lesson23.hibernate.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;
import ru.learnUp.lesson23.hibernate.dao.entity.Client;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderDetails;
import ru.learnUp.lesson23.hibernate.dao.services.ClientService;

import java.util.ArrayList;
import java.util.List;


@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BooksOrderView {
    private Long id;

    private ClientView client;

    private int orderCost;

    public BooksOrderView mapToView(BooksOrder booksOrder) {
        BooksOrderView view = new BooksOrderView();
        view.setId(booksOrder.getId());
        view.setClient(new ClientView(booksOrder.getClient().getId(),
                booksOrder.getClient().getFullName(),
                booksOrder.getClient().getBirthDate()));
        view.setOrderCost(booksOrder.getOrderCost());
//        int cost = 0;
//        booksOrder.getDetails().stream()
//                .map(OrderDetails::getPriceOfBook)
//                .forEach(price -> cost += price)));
//        view.setOrderCost();
        return view;
    }

    public List<BooksOrderView> mapToViewList(List<BooksOrder> booksOrders) {
        List<BooksOrderView> views = new ArrayList<>();
        booksOrders.forEach(booksOrder -> {
            BooksOrderView view = new BooksOrderView();
            view.setId(booksOrder.getId());
            view.setClient(new ClientView(booksOrder.getClient().getId(),
                    booksOrder.getClient().getFullName(),
                    booksOrder.getClient().getBirthDate()));
            view.setOrderCost(booksOrder.getOrderCost());
            views.add(view);
        });
        return views;
    }

    public BooksOrder mapFromView(BooksOrderView view, ClientService clientService) {
        BooksOrder booksOrder = new BooksOrder();
        booksOrder.setId(view.getId());
        booksOrder.setClient(clientService.getClientById(view.getClient().getId()));
        booksOrder.setOrderCost(view.getOrderCost());
        return booksOrder;
    }
}
