package ru.learnUp.lesson23.hibernate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;
import ru.learnUp.lesson23.hibernate.dao.entity.Book;
import ru.learnUp.lesson23.hibernate.dao.services.AuthorService;
import ru.learnUp.lesson23.hibernate.dao.services.BookService;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/bookshop")
public class BookShopController {

    private final ApplicationContext context;

    public BookShopController(ApplicationContext context) {
        this.context = context;
    }

    // http://localhost:8080/bookshop/
    @GetMapping
    public String home(Model model) {
        return "home";
    }

    // http://localhost:8080/bookshop/books
    @GetMapping("/books")
    public String books(Model model) {

        BookService bookService = context.getBean((BookService.class));
        List<Book> books = bookService.getBooks();

        model.addAttribute(
                "books",
                books
        );
        return "books";
    }

    // http://localhost:8080/bookshop/authors
    @GetMapping("/authors")
    public String authors(Model model) {

        AuthorService authorService = context.getBean((AuthorService.class));
        List<Author> authors = authorService.getAuthors();

        model.addAttribute(
                "authors",
                authors
        );
        return "authors";
    }

//    @GetMapping("/book")
//    public String createPage(Model model) {
//        model.addAttribute("book", new Book());
//        return "addBook";
//    }

//    @PostMapping("/addBook")
//    public String createBook(@ModelAttribute Book book, Model model) {
//        log.debug("{}", book);
//        model.addAttribute("book", book);
//        return "addBook";
//    }

}