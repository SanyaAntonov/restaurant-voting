package ru.javaops.bootjava.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dish")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 100000)
    private int price;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
