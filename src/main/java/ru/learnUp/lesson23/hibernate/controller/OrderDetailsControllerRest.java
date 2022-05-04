package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderDetails;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;
import ru.learnUp.lesson23.hibernate.dao.services.BooksOrderService;
import ru.learnUp.lesson23.hibernate.dao.services.OrderDetailsService;
import ru.learnUp.lesson23.hibernate.view.OrderDetailsFromView;
import ru.learnUp.lesson23.hibernate.view.OrderDetailsToView;

import javax.persistence.EntityExistsException;

@RestController
@RequestMapping("rest/order_details")
public class OrderDetailsControllerRest {

    public final OrderDetailsService detailsService;
    public final OrderDetailsToView mapper;
    public final OrderDetailsFromView mapperFrom;
    public final BookService bookService;
    public final BooksOrderService orderService;

    public OrderDetailsControllerRest(OrderDetailsService detailsService, OrderDetailsToView mapper,
                                      OrderDetailsFromView mapperFrom,
                                      BookService bookService, BooksOrderService orderService) {
        this.detailsService = detailsService;
        this.mapper = mapper;
        this.mapperFrom = mapperFrom;
        this.bookService = bookService;
        this.orderService = orderService;
    }

//    // get orders
//    @GetMapping
//    public List<BooksOrderView> getOrders(
//            @RequestParam(value = "clientName", required = false) String clientName
//    ) {
//        return mapper.mapToViewList(orderService.getOrdersBy(new OrderFilter(clientName)));
//    }

    @GetMapping("/{order_detailsId}")
    public OrderDetailsToView getDetails(@PathVariable("order_detailsId") Long order_detailsId) {
        return mapper.mapToView(detailsService.getOrderDetailById(order_detailsId));
    }

    // add order
    @PostMapping
    public OrderDetailsToView createOrderDetail(@RequestBody OrderDetailsFromView body) {
        if (body.getId() != null) {
            throw new EntityExistsException("Id must be null");
        }
        OrderDetails orderDetails = mapperFrom.mapFromView(body, orderService, bookService);
        OrderDetails createdOrderDetails = detailsService.createOrderDetail(orderDetails);
        return mapper.mapToView(createdOrderDetails);
    }

//    // update order
//    @PutMapping("/{orderId}")
//    public BooksOrderView updateOrder(
//            @PathVariable("orderId") Long orderId,
//            @RequestBody BooksOrderView body
//    ) {
//        if (body.getId() == null) {
//            throw new EntityNotFoundException("Try to found null entity");
//        }
//        if (!Objects.equals(orderId, body.getId())) {
//            throw new RuntimeException("Entity has bad id");
//        }
//
//        BooksOrder order = orderService.getBooksOrderById(orderId);
//
//        if (order.getOrderCost() != body.getOrderCost()) {
//            order.setOrderCost(body.getOrderCost());
//        }
//
//        BooksOrder updated = orderService.update(order);
//
//        return mapper.mapToView(updated);
//    }

    // delete orderDetail
    @DeleteMapping("/{order_detailsId}")
    public Boolean deleteOrderDetail(@PathVariable("order_detailsId") Long id) {
        return detailsService.delete(id);
    }
}
