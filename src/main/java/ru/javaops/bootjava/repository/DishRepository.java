package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Dish;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Query("select d from Dish d where d.restaurant.id=:id order by d.price")
    Optional<List<Dish>> getAllByRestaurant(@Param("id") int id);

    @Query("select d from Dish d where d.restaurant.id=:restId and d.id=:id")
    Optional<Dish> get(@Param("restId") int restId, @Param("id") int id);

}
