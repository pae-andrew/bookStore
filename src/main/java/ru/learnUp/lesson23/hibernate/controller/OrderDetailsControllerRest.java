package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.services.OrderDetailsService;
import ru.learnUp.lesson23.hibernate.view.OrderDetailsView;

@RestController
@RequestMapping("rest/order_details")
public class OrderDetailsControllerRest {

    public final OrderDetailsService detailsService;
    public final OrderDetailsView mapper;

    public OrderDetailsControllerRest(OrderDetailsService detailsService, OrderDetailsView mapper) {
        this.detailsService = detailsService;
        this.mapper = mapper;
    }

//    // get orders
//    @GetMapping
//    public List<BooksOrderView> getOrders(
//            @RequestParam(value = "clientName", required = false) String clientName
//    ) {
//        return mapper.mapToViewList(orderService.getOrdersBy(new OrderFilter(clientName)));
//    }

    @GetMapping("/{order_detailsId}")
    public OrderDetailsView getDetails(@PathVariable("order_detailsId") Long order_detailsId) {
        return mapper.mapToView(detailsService.getOrderDetailById(order_detailsId));
    }

//    // add order
//    @PostMapping
//    public BooksOrderView createOrder(@RequestBody BooksOrderView body) {
//        if (body.getId() != null) {
//            throw new EntityExistsException("Id must be null");
//        }
//        BooksOrder order = mapper.mapFromView(body, clientService);
//        BooksOrder createdOrder = orderService.createBooksOrder(order);
//        return mapper.mapToView(createdOrder);
//    }
//
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
//
//    // delete order
//    @DeleteMapping("/{orderId}")
//    public Boolean deleteOrder(@PathVariable("orderId") Long id) {
//        return orderService.delete(id);
//    }
}
