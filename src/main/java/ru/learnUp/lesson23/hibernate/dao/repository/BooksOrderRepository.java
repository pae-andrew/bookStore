package ru.learnUp.lesson23.hibernate.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnUp.lesson23.hibernate.dao.entity.BooksOrder;

@Repository
public interface BooksOrderRepository extends JpaRepository<BooksOrder, Long> {
}
