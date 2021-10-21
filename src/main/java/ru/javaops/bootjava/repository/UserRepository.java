package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaops.bootjava.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailIgnoreCase(String email);
}