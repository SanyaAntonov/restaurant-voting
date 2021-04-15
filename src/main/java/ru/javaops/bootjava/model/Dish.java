package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class Dish extends BaseEntity {
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
