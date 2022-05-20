package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.stereotype.Service;
import ru.learnUp.lesson23.hibernate.dao.entity.OrderHistory;
import ru.learnUp.lesson23.hibernate.dao.repository.OrderHistoryRepository;

import java.util.List;

@Service
public class OrderHistoryService {

    private final OrderHistoryRepository historyRepository;

    public OrderHistoryService(OrderHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void create(OrderHistory orderHistory) {
        historyRepository.save(orderHistory);
    }

    public List<OrderHistory> getAll() {return historyRepository.findAll();}
}
