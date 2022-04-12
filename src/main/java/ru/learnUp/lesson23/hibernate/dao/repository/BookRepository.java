package ru.learnUp.lesson23.hibernate.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.redis.core.RedisHash;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from book b " +
            "left join author a on b.author_id = a.id " +
            "where a.full_name = ?1",
            nativeQuery = true)
    List<Book> findByAuthor(String fullName);

    @Query(value = "select * from book b " +
            "where b.id = ?1",
            nativeQuery = true)
    Book findBook1(Long id);
}
