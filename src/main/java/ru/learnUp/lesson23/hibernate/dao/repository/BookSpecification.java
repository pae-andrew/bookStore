package ru.learnUp.lesson23.hibernate.dao.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.filters.BookFilter;

import javax.persistence.criteria.Predicate;

public class BookSpecification{

    public static Specification<Book> byFilter(BookFilter filter) {

        return (root, q, cb) -> {

            Predicate predicate = cb.isNotNull(root.get("id"));

            if (filter.getName() != null) {
                predicate = cb.and(predicate, cb.like(root.get("name"), "%" + filter.getName() + "%"));
            }
            return predicate;
        };
    }
}
