package ru.learnUp.lesson23.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleStateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.services.*;

@Slf4j
@SpringBootApplication
@EnableCaching
public class HibernateApplication {

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(HibernateApplication.class, args);

		BookService bookService = context.getBean(BookService.class);
//		BookStorageService bookStorageService = context.getBean((BookStorageService.class));

		// -- Check method Searching book by author
//		log.info("Search result: {}", bookService.getBookByAuthor("Erich Maria Remarque"));

		// -- Check of methods but book and add count of books to storage
//		Long id = 2L;
//		log.info("Rest of {} book = {}", id, bookStorageService.buyBook(bookService.getBookById(id), 2));
//		log.info("Rest of {} book = {}", id, bookStorageService.addBook(bookService.getBookById(id), 5));

		// -- Check locks for update bookStorage
//		updateAsync(bookStorageService, bookService.getBookById(1L));
	}

	static void updateAsync(BookStorageService service, Book book) {

		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				try {
					service.buyBook(book, 2);
					log.info("Cool, you just bought this book!");
				} catch (ObjectOptimisticLockingFailureException | StaleStateException e) {
					log.warn("Sorry, you can't buy this book... try again later");
				}
			}).start();
		}
	}
}
