package ru.learnUp.lesson23.hibernate.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.redis.core.RedisHash;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;

@Repository
public interface BookStorageRepository extends JpaRepository<BookStorage, Long> {

    @Query(value = "select * from book_storage bs " +
            "where bs.book_id = ?1 for update",
            nativeQuery = true)
    BookStorage getByBook(Book book);
}
