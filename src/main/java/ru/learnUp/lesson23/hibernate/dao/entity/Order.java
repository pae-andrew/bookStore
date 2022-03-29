package ru.learnUp.lesson23.hibernate.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Client client;

    @Column
    private int orderCost;
}
