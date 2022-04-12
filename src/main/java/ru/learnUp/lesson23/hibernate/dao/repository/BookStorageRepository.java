package ru.learnUp.lesson23.hibernate.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.redis.core.RedisHash;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;

@Repository
public interface BookStorageRepository extends JpaRepository<BookStorage, Long> {
}
