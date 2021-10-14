package ru.javaops.bootjava.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(name = "title", nullable = false)
    @Size(min = 2, max = 50)
    @NotBlank
    private String title;
    @Column(name = "address", nullable = false)
    @Size(min = 2, max = 200)
    @NotBlank
    private String address;
}
