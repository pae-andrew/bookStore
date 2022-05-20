package ru.learnUp.lesson23.hibernate.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderHistory;
import ru.learnUp.lesson23.hibernate.dao.entity.User;
import ru.learnUp.lesson23.hibernate.dao.services.OrderHistoryService;
import ru.learnUp.lesson23.hibernate.dao.services.UserService;
import ru.learnUp.lesson23.hibernate.messagesService.MessageProducer;

import java.util.List;
import java.util.Calendar;

@RestController
@RequestMapping("/history")
public class OrderHistoryControllerRest {

    private final OrderHistoryService historyService;
    private final UserService userService;
    private final MessageProducer messageProducer;

    public OrderHistoryControllerRest(OrderHistoryService historyService, UserService userService, MessageProducer messageProducer) {
        this.historyService = historyService;
        this.userService = userService;
        this.messageProducer = messageProducer;
    }

    @GetMapping
    public void getHistory() {
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, -30);
        User user = userService.loadUserByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());

        List<OrderHistory> resHistories = historyService.getAll().stream()
                .filter(history -> history.getClient().equals(user.getClient()))
                .filter(history -> history.getCal().after(startDate))
                .toList();

        StringBuilder result = new StringBuilder("");
        resHistories.stream()
                .forEach(resHistory -> result.append(resHistory.toString()));

        messageProducer.sendMessage(result.toString());
    }
}
