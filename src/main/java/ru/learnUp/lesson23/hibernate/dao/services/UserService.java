package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.learnUp.lesson23.hibernate.dao.entity.Role;
import ru.learnUp.lesson23.hibernate.dao.entity.User;
import ru.learnUp.lesson23.hibernate.dao.repository.RoleRepository;
import ru.learnUp.lesson23.hibernate.dao.repository.UserRepository;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void create(User user) {
        User exist = userRepository.findByUsername(user.getUsername());
        if (exist != null) {
            throw new EntityExistsException("login with login "
                    + user.getUsername() + " already exist");
        }
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        Set<String> roles = user.getRoles().stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());

        Set<Role> existRoles = roleRepository.findByRoleIn(roles);
        user.setRoles(existRoles);
        existRoles.forEach(role -> role.setUsers(Set.of(user)));
        userRepository.save(user);
    }

    public void addRole(User user, Role role) {

    }

    public User findById(Long id) {
        return userRepository.getById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}