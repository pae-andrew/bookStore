package ru.learnUp.lesson23.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.entity.BookStorage;
import ru.learnUp.lesson23.hibernate.dao.services.*;

@SpringBootApplication
public class HibernateApplication {

	public static final Logger log = LoggerFactory.getLogger(HibernateApplication.class);
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(HibernateApplication.class, args);

		AuthorService author = context.getBean(AuthorService.class);

		BookService bookService = context.getBean(BookService.class);

		BookStorageService bookStorageService = context.getBean(BookStorageService.class);

		ClientService clientService = context.getBean(ClientService.class);

		BooksOrderService booksOrderService = context.getBean(BooksOrderService.class);

		log.info("authors: {}", author.getAuthors());
		log.info("books: {}", bookService.getBooks());
		log.info("book storages: {}", bookStorageService.getBookStorage());
		log.info("clients: {}", clientService.getClients());
		log.info("book orders: {}", booksOrderService.getBooksOrders());
	}

}
