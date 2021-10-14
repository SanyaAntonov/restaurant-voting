package ru.javaops.bootjava.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Size(max = 128)
    private String firstName;
    @Size(max = 128)
    private String lastName;
    @Size(max = 256)
    private String password;
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"},
                    name = "user_roles_unique")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;
}