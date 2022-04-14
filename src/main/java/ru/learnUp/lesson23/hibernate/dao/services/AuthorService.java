package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;
import ru.learnUp.lesson23.hibernate.dao.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.getById(id);
    }

    @Transactional
    @CacheEvict(value = "author", key = "#author.id")
    public void update(Author author) {
            authorRepository.save(author);
    }
}
