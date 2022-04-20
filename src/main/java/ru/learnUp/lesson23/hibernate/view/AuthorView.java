package ru.learnUp.lesson23.hibernate.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorView {

    private Long id;

    private String fullName;

    public AuthorView mapToView(Author author) {
        AuthorView view = new AuthorView();
        view.setId(author.getId());
        view.setFullName(author.getFullName());
        return view;
    }

    public Author mapToView(AuthorView view) {
        Author author = new Author();
        author.setId(view.getId());
        author.setFullName(view.getFullName());
        return author;
    }
}
