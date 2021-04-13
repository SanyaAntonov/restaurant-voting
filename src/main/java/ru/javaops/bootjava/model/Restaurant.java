package ru.javaops.bootjava.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends BaseEntity{
    @Column(name = "title", nullable = false)
    @Size(min = 2, max = 50)
    @NotBlank
    private String title;

    @Column(name = "address", nullable = false)
    @Size(min = 2, max = 200)
    @NotBlank
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="restaurant")
    @OrderBy("date DESC")
    private List<Dish> dishes;
}
