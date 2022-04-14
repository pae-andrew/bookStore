package ru.learnUp.lesson23.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.repository.BookRepository;
import ru.learnUp.lesson23.hibernate.dao.services.*;

@Slf4j
@SpringBootApplication
@EnableCaching
public class HibernateApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(HibernateApplication.class, args);


		BookRepository bookRepository = context.getBean(BookRepository.class);

		BookService bookService = context.getBean(BookService.class);
		BookStorageService bookStorageService = context.getBean((BookStorageService.class));

		log.info("Search result: {}", bookRepository.findByAuthor("Erich Maria Remarque"));

		for (int i = 0; i < 5; i++) {
			log.info("Book id = 1: {}", bookService.getBookById(1L));
		}
//		log.info("Rest of first book = {}", bookStorageService.buyBook(bookService.getBookById(2L), 2));
//		log.info("Rest of first book = {}", bookStorageService.addBook(bookService.getBookById(2L), 2));
	}
}
