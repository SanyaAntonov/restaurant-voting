package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.javaops.bootjava.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @RestResource(rel = "by-email", path = "by-email")
    Optional<User> findByEmailIgnoreCase(String email);
}