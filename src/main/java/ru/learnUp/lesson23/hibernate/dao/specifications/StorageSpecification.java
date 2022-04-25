package ru.learnUp.lesson23.hibernate.dao.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.filters.BookFilter;
import ru.learnUp.lesson23.hibernate.dao.filters.StorageFilter;

import javax.persistence.criteria.Predicate;

public class StorageSpecification {

    public static Specification<BookStorage> byFilter(StorageFilter filter) {

        return (root, q, cb) -> {

            Predicate predicate = cb.isNotNull(root.get("id"));

            if (filter.getBook() != null) {
                predicate = cb.and(predicate, cb.like(root.get("name"), "%" + filter.getBook() + "%"));
            }
            return predicate;
        };
    }

}
