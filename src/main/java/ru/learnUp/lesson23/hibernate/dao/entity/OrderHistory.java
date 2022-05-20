package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Getter
@Setter
@ToString
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Client client;

    @OneToOne
    private BooksOrder order;

    @Column
    private Calendar cal;

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd.MM.YYYY");
        return "Order №: " + order.getId() + "\n"
                + "Date: " + df.format(cal.getTime()) + "\n"
                + "Order cost: " + order.getOrderCost() + "\n";
    }
}
