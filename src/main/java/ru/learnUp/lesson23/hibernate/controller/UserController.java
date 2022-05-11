package ru.learnUp.lesson23.hibernate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.Role;
import ru.learnUp.lesson23.hibernate.dao.entity.User;
import ru.learnUp.lesson23.hibernate.dao.repository.RoleRepository;
import ru.learnUp.lesson23.hibernate.dao.services.UserService;
import ru.learnUp.lesson23.hibernate.view.BookView;
import ru.learnUp.lesson23.hibernate.view.RoleView;
import ru.learnUp.lesson23.hibernate.view.UserView;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping
    public Boolean createUser(@RequestBody UserView user) {
        log.info("HI!");
        User entity = new User();
        Set<Role> roles = user.getRoles().stream()
                .map(role -> roleRepository.findByName(role.getRole()))
                .collect(Collectors.toSet());

        entity.setUsername(user.getLogin());
        entity.setPassword(user.getPassword());
        entity.setRoles(roles);
//        entity.setRoles(
//                user.getRoles()
//                        .stream()
//                        .map(RoleView::getRole)
//                        .map(Role::new)
//                        .collect(Collectors.toSet())
//        );
        userService.create(entity);
        return true;
    }

    @GetMapping("/{userId}")
    public UserView getUser(@PathVariable("userId") Long userId) {
        UserView view = new UserView();
        User user = userService.findById(userId);
        Set<RoleView> roles = user.getRoles().stream()
                .map(role -> new RoleView(role.getRole()))
                .collect(Collectors.toSet());
        view.setLogin(user.getUsername());
        view.setPassword(user.getPassword());
        view.setRoles(roles);
        return view;
    }

}
