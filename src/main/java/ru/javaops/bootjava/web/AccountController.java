package ru.javaops.bootjava.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.AuthUser;
import ru.javaops.bootjava.model.Role;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
@Slf4j
public class AccountController {

    private final UserRepository userRepository;

    @GetMapping
    public User get(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get User {}", authUser);
        return authUser.getUser();
    }

    @DeleteMapping
    public void delete(@AuthenticationPrincipal AuthUser authUser) {
        log.info("delete {}", authUser);
        userRepository.deleteById(authUser.getUser().getId());
    }

    @PostMapping(value = "/register")
    public User register(@Valid @RequestBody User user) {
        log.info("register {}", user);
        user.setRoles(List.of(Role.USER));
        return userRepository.save(user);
    }

    @PutMapping
    public void update(@Valid @RequestBody User user, @AuthenticationPrincipal AuthUser authUser) {
        log.info("update {} to {}", authUser, user);
        User oldUser = authUser.getUser();
        user.setRoles(oldUser.getRoles());
        if (user.getPassword() == null) {
            user.setPassword(oldUser.getPassword());
        }
        userRepository.save(user);
    }
}
