package ru.javaops.bootjava.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class Dish extends BaseEntity {
    public Dish(Integer id, @NotBlank @Size(min = 2, max = 50) String name, @Range(min = 10, max = 100000) int price, @NotNull Restaurant restaurant) {
        this(name, price, restaurant);
        this.id = id;
    }

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 100000)
    private int price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @NotNull
    private Restaurant restaurant;
}
