package ru.learnUp.lesson23.hibernate.dao.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.learnUp.lesson23.hibernate.dao.entity.Author;

@Data
@AllArgsConstructor
public class BookFilter {

    private final String name;
}
