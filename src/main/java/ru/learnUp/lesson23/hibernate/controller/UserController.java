package ru.learnUp.lesson23.hibernate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.entity.User;
import ru.learnUp.lesson23.hibernate.dao.repository.RoleRepository;
import ru.learnUp.lesson23.hibernate.dao.services.ClientService;
import ru.learnUp.lesson23.hibernate.dao.services.UserService;
import ru.learnUp.lesson23.hibernate.view.UserView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserView mapping;
    private final RoleRepository roleRepository;
    private final ClientService clientService;

    public UserController(UserService userService, RoleRepository roleRepository, UserView mapping, ClientService clientService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.mapping = mapping;
        this.clientService = clientService;
    }

    @PostMapping
    public Boolean createUser(@RequestBody UserView view) {
        User user = userService.create(mapping.mapFromView(view, roleRepository, clientService));
        clientService.getClientByName(user.getUsername()).setUser(user);
        clientService.update(clientService.getClientByName(user.getUsername()));
        return true;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<UserView> getUsers() {
        return mapping.mapToView(userService.findAll());
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{userId}")
    public UserView getUser(@PathVariable("userId") Long userId) {
        return mapping.mapToView(userService.findById(userId));
    }

}
