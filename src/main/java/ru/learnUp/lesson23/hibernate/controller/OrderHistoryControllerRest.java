package ru.learnUp.lesson23.hibernate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderHistory;
import ru.learnUp.lesson23.hibernate.dao.entity.User;
import ru.learnUp.lesson23.hibernate.dao.services.OrderHistoryService;
import ru.learnUp.lesson23.hibernate.dao.services.UserService;
import ru.learnUp.lesson23.hibernate.messagesService.FileDirectoryProvider;
import ru.learnUp.lesson23.hibernate.messagesService.MessageProducer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Calendar;

@Slf4j
@RestController
@RequestMapping("/history")
public class OrderHistoryControllerRest {

    private final OrderHistoryService historyService;
    private final UserService userService;
    private final MessageProducer messageProducer;
    private final FileDirectoryProvider fileDirectoryProvider;

    public OrderHistoryControllerRest(OrderHistoryService historyService,
                                      UserService userService,
                                      MessageProducer messageProducer, FileDirectoryProvider fileDirectoryProvider) {
        this.historyService = historyService;
        this.userService = userService;
        this.messageProducer = messageProducer;
        this.fileDirectoryProvider = fileDirectoryProvider;
    }

    @GetMapping
    public String getHistory() {
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

        DateFormat df = new SimpleDateFormat("dd.MM.YYYY");
        StringBuilder fileName = new StringBuilder("" + user.getUsername() + "_"
        + df.format(Calendar.getInstance().getTime()));

        try {
            fileDirectoryProvider.writeString(fileName.toString(), result.toString());
        } catch (IOException e) {
            log.error("Can't write to file...");
        }
        return fileName.toString();
    }
}
