package ru.javaops.bootjava.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends BaseEntity {
    @Column(name = "title", nullable = false)
    @Size(min = 2, max = 50)
    @NotBlank
    private String title;
    @Column(name = "address", nullable = false)
    @Size(min = 2, max = 200)
    @NotBlank
    private String address;

    public Restaurant(Integer id, @Size(min = 2, max = 50) @NotBlank String title, @Size(min = 2, max = 200) @NotBlank String address) {
        this(title, address);
        this.id = id;
    }
}
