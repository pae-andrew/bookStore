package ru.learnUp.lesson23.hibernate.dao.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fullName;

    @Column
    private Date birthDate;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @Nullable
    private User user;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BooksOrder> orders;
}
